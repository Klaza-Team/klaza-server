// Plugin Klaza para Moodle - Server - AttemptService.kt
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
import klaza.klaza_server.data.EventData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AttemptService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AttemptService::class.java)
    }

    @Autowired lateinit var notificationService: NotificationService

    fun attemptSubmitted(eventData: EventData) {

        LOGGER.info(Colors.GREEN + "attemptSubmitted -> $eventData" + Colors.RESET)

        notificationService.sendNotification(eventData)

    }

}