package klaza.klaza_server.services

import klaza.klaza_server.data.EventData
import klaza.klaza_server.libs.Colors
import klaza.klaza_server.models.KlazaDiscordInstance
import klaza.klaza_server.models.KlazaUserInstance
import klaza.klaza_server.models.User
import klaza.klaza_server.models.UserInfoData
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

    fun sendNotification(eventData: EventData) {

        val discordInstances = klazaDiscordInstanceRepository.findAllByCourse_Id(eventData.course!!.getId()!!)
        val telegramInstances = klazaTelegramInstanceRepository.findAllByCourse_Id(eventData.course!!.getId()!!)

        val users = userRepository.findAllByCourseID(eventData.course!!.getId()!!)

        val userInstances = sortUserInstancesByPriority(eventData.course!!.getId()!!, users)

        // TODO: Redirecionar para o serviço de notificação do bot específico

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

        LOGGER.info(Colors.GREEN + "User instances: $map" + Colors.RESET)

        return map

    }

}