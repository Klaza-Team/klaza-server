package klaza.klaza_server.services

import klaza.klaza_server.data.EventData
import klaza.klaza_server.libs.Colors
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CourseModuleService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CourseModuleService::class.java)
    }

    fun created(eventData: EventData) {
        LOGGER.info(Colors.GREEN + "CourseModuleService created -> $eventData" + Colors.RESET)
    }

    fun updated(eventData: EventData) {
        LOGGER.info(Colors.GREEN + "CourseModuleService updated -> $eventData" + Colors.RESET)
    }

    fun deleted(eventData: EventData) {
        LOGGER.info(Colors.GREEN + "CourseModuleService deleted -> $eventData" + Colors.RESET)
    }

}