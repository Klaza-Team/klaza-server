// Plugin Klaza para Moodle - Server - Cron.kt
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


package klaza.klaza_server.classes

import klaza.klaza_server.data.EventData
import klaza.klaza_server.services.CronService
import klaza.klaza_server.services.NotificationService
import org.slf4j.LoggerFactory
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class Cron(
    private var cronService: CronService,
    private var notificationService: NotificationService,
    private var eventData: EventData,
    var time: Date,
    var name: String) : TimerTask() {

    companion object {
        val LOGGER = LoggerFactory.getLogger(Cron::class.java)
    }

    private val timer = Timer()

    init {
        timer.schedule(this, time)
        eventData = recreateEventData()
    }

    override fun run() {

        LOGGER.info(Colors.BLUE + "Execute cron: $name - $time" + Colors.RESET)

        notificationService.sendNotification(eventData)

        cronService.deleteCronJob(eventData, name)

    }

    override fun toString(): String {
        return "Cron(eventData=$eventData, time=$time, name='$name')"
    }

    fun recreateEventData(): EventData {

        return EventData(
            eventname = name,
            relatedassign = eventData.relatedassign,
            relatedquiz = eventData.relatedquiz,
            relateduser = eventData.relateduser,
            action = eventData.action,
            contextid = eventData.contextid,
            contextlevel = eventData.contextlevel,
            course = eventData.course,
            crud = eventData.crud,
            objectid = eventData.objectid,
            other = eventData.other,
            target = eventData.target,
            user = eventData.user,
        )

    }

}