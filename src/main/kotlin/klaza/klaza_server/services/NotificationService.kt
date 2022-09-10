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

import klaza.klaza_server.components.DiscordComponent
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
//    @Autowired lateinit var telegramComponent: TelegramComponent

    fun sendNotification(eventData: EventData) {

        val discordInstances = klazaDiscordInstanceRepository.findAllByCourse_Id(eventData.course!!.getId()!!)
        val telegramInstances = klazaTelegramInstanceRepository.findAllByCourse_Id(eventData.course!!.getId()!!)

        val users = userRepository.findAllByCourseID(eventData.course!!.getId()!!)

        val userInstances = sortUserInstancesByPriority(eventData.course!!.getId()!!, users)

        // DISCORD
        discordComponent.sendServerNotifications(eventData, discordInstances)

        // TELEGRAM

        // WHATSAPP

        // EMAIL

        sendNotificationToUsers(eventData, userInstances)

        // TODO: Redirecionar para o serviço de notificação do TELEGRAM
        // TODO: Redirecionar para o serviço de notificação do WHATSAPP

    }

    fun sortUserInstancesByPriority(courseID: Long, users: List<User>): MutableMap<User, List<Map<String, String>>> {

        val map = mutableMapOf<User, List<Map<String, String>>>()

        for (u in users) {

            val userInfo = mutableMapOf<String, String>()

           userInfoDataRepository.findAllKlazaUserInfoDataByUserId(u.getId()!!).forEach { it ->
               userInfo[it.getField()!!.getShortname()!!] = it.getData()!!
           }

            val userDiscordInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.getId()!!, courseID, "DISCORD")
            val userTelegramInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.getId()!!, courseID, "TELEGRAM")
            val userWhatsappInstance = klazaUserInstanceRepository.findOneByUser_IdAndCourse_IdAndType(u.getId()!!, courseID, "WHATSAPP")

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
                map[u] = userList
            }

        }

//        LOGGER.info(Colors.GREEN + "User instances: $map" + Colors.RESET)

        return map

    }

    fun sendNotificationToUsers(eventData: EventData, instances: MutableMap<User, List<Map<String, String>>>) {

//        LOGGER.info(instances.toString())

        for (u in instances.keys) {

           val userInstances = instances[u]!!

              for (instance in userInstances) {

                  val i = userInstances.indexOf(instance)

                  when (instance["type"]) {
                      "DISCORD" -> {
                          if (discordComponent.sendUserNotification(eventData, instance["value"]!!, i == 0)) { break } else { continue }
                      }
                      "TELEGRAM" -> {
                          if (discordComponent.sendUserNotification(eventData, instance["value"]!!, i == 0)) { break } else { continue }
                      }
                      "WHATSAPP" -> {
                          if (whatsAppComponent.sendUserNotification(eventData, instance["value"]!!, i == 0)) { break } else { continue }
                      }
                  }

              }

        }

    }

}