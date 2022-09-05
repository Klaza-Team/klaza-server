package klaza.klaza_server.services

import klaza.klaza_server.data.EventData
import klaza.klaza_server.libs.Colors
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseModuleService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CourseModuleService::class.java)
    }

    @Autowired lateinit var notificationService: NotificationService


    fun created(eventData: EventData) {
        LOGGER.info(Colors.GREEN + "CourseModuleService created -> $eventData" + Colors.RESET)
    }

    fun updated(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "CourseModuleService updated -> $eventData" + Colors.RESET)

        if (needCron(eventData)) {
            // TODO: Adicionar service/componente para gerar cron
        }

        notificationService.sendNotification(eventData)

    }

    fun deleted(eventData: EventData) {
        LOGGER.info(Colors.GREEN + "CourseModuleService deleted -> $eventData" + Colors.RESET)
    }



     fun needCron(eventData: EventData): Boolean {
         return eventData.isAssign() || eventData.isQuiz()
     }

}