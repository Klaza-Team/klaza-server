// Plugin Klaza para Moodle - Server - NotificationService.kt
// Copyright (C) 2022 Klaza Team

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.


package klaza.klaza_server.services

import klaza.klaza_server.classes.Colors
import klaza.klaza_server.components.DiscordComponent
import klaza.klaza_server.components.EmailComponent
import klaza.klaza_server.components.TelegramComponent
import klaza.klaza_server.components.WhatsAppComponent
import klaza.klaza_server.data.EventData
import klaza.klaza_server.dtos.UserNotificationContactDTO
import klaza.klaza_server.models.User
import klaza.klaza_server.repositories.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(NotificationService::class.java)
    }

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var klazaDiscordInstanceRepository: KlazaDiscordInstanceRepository
    @Autowired lateinit var klazaTelegramInstanceRepository: KlazaTelegramInstanceRepository
    @Autowired lateinit var klazaUserInstanceRepository: KlazaUserInstanceRepository
    @Autowired lateinit var userInfoDataRepository: UserInfoDataRepository
    @Autowired lateinit var discordComponent: DiscordComponent
    @Autowired lateinit var whatsAppComponent: WhatsAppComponent
    @Autowired lateinit var telegramComponent: TelegramComponent
    @Autowired lateinit var emailComponent: EmailComponent
    @Autowired lateinit var userService: UserService

    fun sendNotification(eventData: EventData) {

        if (CronService.getCronNamePattern().matches(eventData.eventname)) {
            sendCourseNotification(eventData)
        }
        else {

            when (eventData.eventname) {

                "\\core\\event\\course_module_created", "\\core\\event\\course_module_updated", "\\core\\event\\course_module_deleted" -> { sendCourseNotification(eventData) }
                "\\core\\event\\message_sent" -> { sendMessageNotification(eventData) }
                "\\assignsubmission_file\\event\\submission_updated" -> { sendSubmissionNotification(eventData) }
                "\\mod_quiz\\event\\attempt_submitted" -> { sendAttemptNotification(eventData) }
                "\\assignsubmission_comments\\event\\comment_created", "\\assignsubmission_comments\\event\\comment_deleted" -> { sendCommentNotification(eventData) }
                else -> { return }

            }

        }

    }

    fun sortUserInstancesByPriority(courseID: Long, users: List<User>): MutableMap<User, List<UserNotificationContactDTO>> {

        val map = mutableMapOf<User, List<UserNotificationContactDTO>>()

        for (u in users) {

            val emailContact = UserNotificationContactDTO("EMAIL", u.email!!, 0)

            val userDiscordInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.id!!, courseID, "DISCORD")
            val userTelegramInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.id!!, courseID, "TELEGRAM")
            val userWhatsappInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.id!!, courseID, "WHATSAPP")

            val loopInstance = listOf(userDiscordInstance, userTelegramInstance, userWhatsappInstance)
            val userContacts = userService.getOnlyAllowedUserNotificationContacts(u.id!!)

            for (instance in loopInstance) {

                if (instance != null) {

                    val contact = userContacts.find { it.type == instance.type } ?: continue

                    if (!map.containsKey(u)) {
                        map[u] = mutableListOf(contact)
                    }
                    else {
                        val list = map[u]!!.toMutableList()
                        list.add(contact)
                        map[u] = list
                    }

                }

            }

            if (map.containsKey(u)) {

                val userList = map[u]!!.toMutableList()

                userList.sortBy { it.priority }
                userList.add(emailContact)

                map[u] = userList

            }
            else {
                map[u] = listOf(emailContact)
            }

        }

//        LOGGER.info(Colors.GREEN + "User instances: $map" + Colors.RESET)

        return map

    }

    fun sendNotificationToUsersInstances(eventData: EventData, instances: MutableMap<User, List<UserNotificationContactDTO>>) {

//        LOGGER.info(instances.toString())

        for (u in instances.keys) {

            val userInstances = instances[u]!!

            for (instance in userInstances) {

                val i = userInstances.indexOf(instance)

                when (instance.type) {
                    "DISCORD" -> {
                        if (discordComponent.sendUserNotification(eventData, instance.value, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "TELEGRAM" -> {
                        if (telegramComponent.sendUserNotification(eventData, instance.value, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "WHATSAPP" -> {
                        if (whatsAppComponent.sendUserNotification(eventData, instance.value, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "EMAIL" -> {
                        emailComponent.sendUserNotification(eventData, u.email!!)
                        break
                    }
                }
            }

        }
    }

    fun sendNotificationToUsers(eventData: EventData, users: List<User>) {

        for (u in users) {

            val userInstances = userService.getOnlyAllowedUserNotificationContacts(u.id!!)

            sendNotificationToUsersInstances(eventData, mutableMapOf(u to userInstances))

        }

    }



    fun sendCourseNotification(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "sendCourseNotification -> $eventData" + Colors.RESET)

        val discordInstances = klazaDiscordInstanceRepository.findAllByCourse_Id(eventData.course!!.id!!)
        val telegramInstances = klazaTelegramInstanceRepository.findAllByCourse_Id(eventData.course!!.id!!)

        val users = userRepository.findAllByCourseID(eventData.course!!.id!!)

        val userInstances = sortUserInstancesByPriority(eventData.course!!.id!!, users)

        // DISCORD
        discordComponent.sendServerNotifications(eventData, discordInstances)

        // TELEGRAM
        telegramComponent.sendServerNotifications(eventData, telegramInstances)

        // USERS
        sendNotificationToUsersInstances(eventData, userInstances)

    }

    fun sendMessageNotification(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "sendMessageNotification -> $eventData" + Colors.RESET)

        sendNotificationToUsers(eventData, listOf(eventData.relateduser!!))

    }

    fun sendSubmissionNotification(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "sendMessageNotification -> $eventData" + Colors.RESET)

        sendNotificationToUsers(eventData, userRepository.findAllTeachersByCourseID(eventData.course!!.id!!))

    }

    fun sendAttemptNotification(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "sendAttemptNotification -> $eventData" + Colors.RESET)

        sendNotificationToUsers(eventData, userRepository.findAllTeachersByCourseID(eventData.course!!.id!!))

    }

    fun sendCommentNotification(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "sendCommentNotification -> $eventData" + Colors.RESET)

        sendNotificationToUsers(eventData, userRepository.findAllTeachersByCourseID(eventData.course!!.id!!))

    }

}