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
import klaza.klaza_server.models.KlazaDiscordInstanceModel
import klaza.klaza_server.models.KlazaTelegramInstanceModel
import klaza.klaza_server.models.UserModel
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
    @Autowired lateinit var userInfoDataRepository: UserInfoDataRepository
    @Autowired lateinit var discordComponent: DiscordComponent
    @Autowired lateinit var whatsAppComponent: WhatsAppComponent
    @Autowired lateinit var telegramComponent: TelegramComponent
    @Autowired lateinit var emailComponent: EmailComponent
    @Autowired lateinit var userService: UserService
    @Autowired lateinit var klazaDiscordAccountRepository: KlazaDiscordAccountModelRepository
    @Autowired lateinit var klazaTelegramAccountRepository: KlazaTelegramAccountModelRepository
    @Autowired lateinit var klazaWhatsAppAccountRepository: KlazaWhatsappAccountModelRepository
    @Autowired lateinit var klazaGlobalConfigRepository: KlazaGlobalConfigRepository
    @Autowired lateinit var klazaDiscordInstanceConfigRepository: KlazaDiscordInstanceConfigRepository
    @Autowired lateinit var klazaTelegramInstanceConfigRepository: KlazaTelegramInstanceConfigRepository

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

    fun sortUserInstancesByPriority(courseID: Long, users: List<UserModel>): MutableMap<UserModel, List<UserNotificationContactDTO>> {

        val map = mutableMapOf<UserModel, List<UserNotificationContactDTO>>()

        for (u in users) {

            val emailContact = UserNotificationContactDTO("EMAIL", u.email!!, 0)

            val userDiscordInstance = klazaDiscordAccountRepository.findByUserId(u.id!!)
            val userTelegramInstance = klazaTelegramAccountRepository.findByUserId(u.id!!)
            val userWhatsappInstance = klazaWhatsAppAccountRepository.findByUserId(u.id!!)

            val loopInstance = listOf(
                if (userDiscordInstance != null) UserNotificationContactDTO("DISCORD", userDiscordInstance.value!!, userDiscordInstance.priority!!) else null,
                if (userTelegramInstance != null) UserNotificationContactDTO("TELEGRAM", userTelegramInstance.value!!, userTelegramInstance.priority!!) else null,
                if (userWhatsappInstance != null) UserNotificationContactDTO("WHATSAPP", userWhatsappInstance.value!!, userWhatsappInstance.priority!!) else null
            )
            val userContacts = userService.getOnlyAllowedUserNotificationContacts(u.id!!)

//            println(loopInstance)
//            println(userContacts)

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

    fun sendNotificationToUsersInstances(eventData: EventData, instances: MutableMap<UserModel, List<UserNotificationContactDTO>>) {

//        LOGGER.info(instances.toString())

        for (u in instances.keys) {

            val userInstances = instances[u]!!

            for (instance in userInstances) {

                val i = userInstances.indexOf(instance)

                when (instance.type) {
                    "DISCORD" -> {
                        if (discordComponent.sendUserNotification(eventData, instance.value, u, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "TELEGRAM" -> {
                        if (telegramComponent.sendUserNotification(eventData, instance.value, u, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "WHATSAPP" -> {
                        if (whatsAppComponent.sendUserNotification(eventData, instance.value, u, i == 0)) {
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

    fun sendNotificationToUsers(eventData: EventData, users: List<UserModel>) {

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

//        println(userInstances)

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

    fun userHasNotificationEnabled(eventData: EventData, user: UserModel): Boolean {

        val userConfig = klazaGlobalConfigRepository.findByUserId(user.id!!)

        if (userConfig != null) {

            if (eventData.eventname == "\\core\\event\\course_module_created") { return userConfig.notifyCreateContent ?: true }
            if (eventData.eventname == "\\core\\event\\course_module_updated") { return userConfig.notifyEditContent ?: true }
            if (eventData.eventname == "\\core\\event\\course_module_deleted") { return userConfig.notifyDeleteContent ?: true }

            if (eventData.eventname == "\\core\\event\\message_sent") { return userConfig.notifyReceiveMessage ?: true }

            if (eventData.eventname == "\\assignsubmission_file\\event\\submission_updated") { return userConfig.notifySendAssignment ?: true }
            if (eventData.eventname == "\\mod_quiz\\event\\attempt_submitted") { return userConfig.notifySendAssignment ?: true }

            if (eventData.eventname == "\\assignsubmission_comments\\event\\comment_created") { return userConfig.notifyReceiveComment ?: true }
            if (eventData.eventname == "\\assignsubmission_comments\\event\\comment_deleted") { return userConfig.notifyDeleteComment ?: true }

            return true

        }
        else {
            return true
        }

    }

    fun discordInstanceHasNotificationEnabled(eventData: EventData, discordInstance: KlazaDiscordInstanceModel): Boolean {

        val config = klazaDiscordInstanceConfigRepository.findByDiscordInstance_Id(discordInstance.id!!)

        if (config != null) {

            if (eventData.eventname == "\\core\\event\\course_module_created") { return config.notifyCreateContent ?: true }
            if (eventData.eventname == "\\core\\event\\course_module_updated") { return config.notifyEditContent ?: true }
            if (eventData.eventname == "\\core\\event\\course_module_deleted") { return config.notifyDeleteContent ?: true }

            if (eventData.eventname == "\\core\\event\\message_sent") { return config.notifyReceiveMessage ?: true }

            if (eventData.eventname == "\\assignsubmission_file\\event\\submission_updated") { return config.notifySendAssignment ?: true }
            if (eventData.eventname == "\\mod_quiz\\event\\attempt_submitted") { return config.notifySendAssignment ?: true }

            if (eventData.eventname == "\\assignsubmission_comments\\event\\comment_created") { return config.notifyReceiveComment ?: true }
            if (eventData.eventname == "\\assignsubmission_comments\\event\\comment_deleted") { return config.notifyDeleteComment ?: true }

        }

        return true

    }

    fun telegramInstanceHasNotificationEnabled(eventData: EventData, telegramInstance: KlazaTelegramInstanceModel): Boolean {

        val config = klazaTelegramInstanceConfigRepository.findByTelegramInstance_Id(telegramInstance.id!!)

        if (config != null) {

            if (eventData.eventname == "\\core\\event\\course_module_created") { return config.notifyCreateContent ?: true }
            if (eventData.eventname == "\\core\\event\\course_module_updated") { return config.notifyEditContent ?: true }
            if (eventData.eventname == "\\core\\event\\course_module_deleted") { return config.notifyDeleteContent ?: true }

            if (eventData.eventname == "\\core\\event\\message_sent") { return config.notifyReceiveMessage ?: true }

            if (eventData.eventname == "\\assignsubmission_file\\event\\submission_updated") { return config.notifySendAssignment ?: true }
            if (eventData.eventname == "\\mod_quiz\\event\\attempt_submitted") { return config.notifySendAssignment ?: true }

            if (eventData.eventname == "\\assignsubmission_comments\\event\\comment_created") { return config.notifyReceiveComment ?: true }
            if (eventData.eventname == "\\assignsubmission_comments\\event\\comment_deleted") { return config.notifyDeleteComment ?: true }

        }

        return true

    }

}