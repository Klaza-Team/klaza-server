// Plugin Klaza para Moodle - Server - CourseModuleService.kt
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

import klaza.klaza_server.data.EventData
import klaza.klaza_server.classes.Colors
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseModuleService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CourseModuleService::class.java)
    }

    @Autowired lateinit var notificationService: NotificationService
    @Autowired lateinit var cronService: CronService

    fun created(eventData: EventData) {
        LOGGER.info(Colors.GREEN + "CourseModuleService created -> $eventData" + Colors.RESET)
    }

    fun updated(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "CourseModuleService updated -> $eventData" + Colors.RESET)

        if (needCron(eventData)) {
            cronService.createCronJob(eventData)
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