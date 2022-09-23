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

    fun sendNotification(eventData: EventData) {

        if (CronService.getCronNamePattern().matches(eventData.eventname)) {
            sendCourseNotification(eventData)
        }
        else {

            when (eventData.eventname) {

                "\\core\\event\\course_module_created", "\\core\\event\\course_module_updated", "\\core\\event\\course_module_deleted" -> { sendCourseNotification(eventData) }
                "\\core\\event\\message_sent" -> { sendMessageNotification(eventData) }
                "\\assignsubmission_file\\event\\submission_updated" -> {}
                "\\mod_quiz\\event\\attempt_submitted" -> {}
                "\\core\\event\\comment_created", "\\core\\event\\comment_deleted" -> {}
                else -> { return }

            }

        }

    }

    fun sortUserInstancesByPriority(courseID: Long, users: List<User>): MutableMap<User, List<Map<String, String>>> {

        val map = mutableMapOf<User, List<Map<String, String>>>()

        for (u in users) {

            val userInfo = mutableMapOf<String, String>()
            val emailMap = mapOf("type" to "EMAIL", "value" to u.email, "priority" to "0") as Map<String, String>

           userInfoDataRepository.findAllKlazaUserInfoDataByUserId(u.id!!).forEach { it ->
               userInfo[it.field!!.shortname!!] = it.data!!
           }

            val userDiscordInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.id!!, courseID, "DISCORD")
            val userTelegramInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.id!!, courseID, "TELEGRAM")
            val userWhatsappInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.id!!, courseID, "WHATSAPP")

            val loopInstance = listOf(userDiscordInstance, userTelegramInstance, userWhatsappInstance)
            val loopInstanceMap = listOf(
                mapOf("type" to "DISCORD", "var" to "klaza_discord", "priority" to "klaza_discord_priority"),
                mapOf("type" to "TELEGRAM", "var" to "klaza_telegram", "priority" to "klaza_telegram_priority"),
                mapOf("type" to "WHATSAPP", "var" to "klaza_whatsapp", "priority" to "klaza_whatsapp_priority")
            )

            for (instance in loopInstance) {

                val instanceMap = loopInstanceMap[loopInstance.indexOf(instance)]

                if (instance != null && userInfo[instanceMap["priority"]] != "-1") {

                    val newValue = mapOf("type" to instanceMap["type"], "value" to (userInfo[instanceMap["var"]] ?: ""), "priority" to (userInfo[instanceMap["priority"]] ?: "-1")) as Map<String, String>

                    if (!map.containsKey(u)) {
                        map[u] = mutableListOf(newValue)
                    }
                    else {
                        val list = map[u]!!.toMutableList()
                        list.add(newValue)
                        map[u] = list
                    }

                }

            }

            if (map.containsKey(u)) {

                val userList = map[u]!!.toMutableList()

                userList.sortBy { it["priority"] }
                userList.add(emailMap)

                map[u] = userList

            }
            else {
                map[u] = listOf(emailMap)
            }

        }

//        LOGGER.info(Colors.GREEN + "User instances: $map" + Colors.RESET)

        return map

    }

    fun sendNotificationToUsersInstances(eventData: EventData, instances: MutableMap<User, List<Map<String, String>>>) {

//        LOGGER.info(instances.toString())

        for (u in instances.keys) {

            val userInstances = instances[u]!!

            for (instance in userInstances) {

                val i = userInstances.indexOf(instance)

                when (instance["type"]) {
                    "DISCORD" -> {
                        if (discordComponent.sendUserNotification(eventData, instance["value"]!!, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "TELEGRAM" -> {
                        if (telegramComponent.sendUserNotification(eventData, instance["value"]!!, i == 0)) {
                            break
                        } else {
                            continue
                        }
                    }
                    "WHATSAPP" -> {
                        if (whatsAppComponent.sendUserNotification(eventData, instance["value"]!!, i == 0)) {
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

            val userInfo = userInfoDataRepository.findAllKlazaUserInfoDataByUserId(u.id!!)



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

        val userInstances = sortUserInstancesByPriority(eventData.course!!.id!!, listOf(eventData.user!!))

    }

}