// Plugin Klaza para Moodle - Server - CronService.kt
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

import klaza.klaza_server.classes.Cron
import klaza.klaza_server.data.EventData
import klaza.klaza_server.models.Assign
import klaza.klaza_server.models.KlazaAssignNotification
import klaza.klaza_server.models.KlazaQuizNotification
import klaza.klaza_server.models.Quiz
import klaza.klaza_server.repositories.KlazaAssignNotificationRepository
import klaza.klaza_server.repositories.KlazaQuizNotificationRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

@Service
class CronService {

    @Autowired lateinit var quizNotificationRepository: KlazaQuizNotificationRepository
    @Autowired lateinit var assignNotificationRepository: KlazaAssignNotificationRepository
    @Autowired lateinit var notificationService: NotificationService

    companion object {

        private val LOGGER = LoggerFactory.getLogger(CronService::class.java)
        private val CRONS = mutableMapOf<String, Cron>()

        fun getCronName(event: EventData, lastDays: Int): String {

            val type = if (event.isQuiz()) "Quiz" else "Assign"
            val days = if (lastDays == 0) "Close" else if (lastDays == 1) "LastDay" else if (lastDays > 1) "Last${lastDays}Days" else "Open"
            val id = if (event.isQuiz()) event.relatedquiz!!.getID()!! else event.relatedassign!!.getId()!!

            return "${type}_${days}_${id}"

        }

    }

    fun createCronJob(eventData: EventData) {

        val map = mapOf(
            "quiz" to mapOf(
                "id" to { evt: EventData -> evt.relatedquiz!!.getID() },
                "related" to { evt: EventData -> evt.relatedquiz!! },
                "find" to { id: Long, name: String -> quizNotificationRepository.findByQuiz_IdAndEventname(id, name) },
                "delete" to { nt: KlazaQuizNotification -> quizNotificationRepository.delete(nt) },
                "save" to { related: Quiz, name: String -> quizNotificationRepository.save(KlazaQuizNotification(related, name)) }
            ),
            "assign" to mapOf(
                "id" to { evt: EventData -> evt.relatedassign!!.getId() },
                "related" to { evt: EventData -> evt.relatedassign!! },
                "find" to { id: Long, name: String -> assignNotificationRepository.findByAssign_IdAndEventname(id, name) },
                "delete" to { nt: KlazaAssignNotification -> assignNotificationRepository.delete(nt) },
                "save" to { related: Assign, name: String -> assignNotificationRepository.save(KlazaAssignNotification(related, name)) }
            )
        )

        if (eventData.isQuiz() || eventData.isAssign()) {

            val key =  if (eventData.isQuiz()) "quiz" else "assign"
            val value = map[key]!!

            val id = (value["id"] as (EventData) -> Long)(eventData)
            val related = (value["related"] as (EventData) -> Any)(eventData)
            val find = if (eventData.isQuiz()) value["find"] as (Long, String) -> KlazaQuizNotification? else value["find"] as (Long, String) -> KlazaAssignNotification?
            val delete = value["delete"] as (Any) -> Unit
            val save = value["save"] as (Any, String) -> Any

            val nameOpen = getCronName(eventData, -1)
            val nameLast2Days = getCronName(eventData, 2)
            val nameLastDay = getCronName(eventData, 1)
            val nameClose = getCronName(eventData, 0)

            if (eventData.crud == "u") {

                if (CRONS[nameOpen] != null) { CRONS[nameOpen]!!.cancel() }
                if (CRONS[nameLast2Days] != null) { CRONS[nameLast2Days]?.cancel() }
                if (CRONS[nameLastDay] != null) { CRONS[nameLastDay]?.cancel() }
                if (CRONS[nameClose] != null) { CRONS[nameClose]?.cancel() }

                CRONS.remove(nameOpen)
                CRONS.remove(nameLast2Days)
                CRONS.remove(nameLastDay)
                CRONS.remove(nameClose)

                val eventOpenDB = find(id, nameOpen)
                val eventLast2DaysDB = find(id, nameLast2Days)
                val eventLastDayDB = find(id, nameLastDay)
                val eventCloseDB = find(id, nameClose)

                if (eventOpenDB != null) { delete(eventOpenDB) }
                if (eventLast2DaysDB != null) { delete(eventLast2DaysDB) }
                if (eventLastDayDB != null) { delete(eventLastDayDB) }
                if (eventCloseDB != null) { delete(eventCloseDB) }

            }

            val openTime = getOpenFromEvent(eventData)
            val last2DaysTime = getLast2DaysFromEvent(eventData)
            val lastDayTime = getLastDayFromEvent(eventData)
            val closeTime = getCloseFromEvent(eventData)

            CRONS[nameOpen] = Cron(this, notificationService, eventData, openTime!!, nameOpen)
            CRONS[nameLast2Days] = Cron(this, notificationService, eventData, last2DaysTime!!, nameLast2Days)
            CRONS[nameLastDay] = Cron(this, notificationService, eventData, lastDayTime!!, nameLastDay)
            CRONS[nameClose] = Cron(this, notificationService, eventData, closeTime!!, nameClose)

            save(related, nameOpen)
            save(related, nameLast2Days)
            save(related, nameLastDay)
            save(related, nameClose)

        }

    }

    fun deleteCronJob(eventData: EventData, eventname: String) {

        if (CRONS[eventname] != null) {

            CRONS[eventname]!!.cancel()
            CRONS.remove(eventname)

        }

        if (eventData.isQuiz()) {

            val entity = quizNotificationRepository.findByQuiz_IdAndEventname(eventData.relatedquiz!!.getID()!!, eventname)
            if (entity != null) { quizNotificationRepository.delete(entity) }

        }
        else {

            val entity = assignNotificationRepository.findByAssign_IdAndEventname(eventData.relatedassign!!.getId()!!, eventname)
            if (entity != null) { assignNotificationRepository.delete(entity) }

        }

    }

    fun getCloseFromEvent(eventData: EventData): Date? {

        if (eventData.isQuiz()) {
            return Date(eventData.relatedquiz!!.getTimeClose()!!)
        }

        if (eventData.isAssign()) {
            return Date(eventData.relatedassign!!.getDueDate()!!)
        }

        return null

    }

    fun getLast2DaysFromEvent(eventData: EventData): Date? {

        val closeTime = getCloseFromEvent(eventData)

        if (closeTime != null) {
            return Date.from(LocalDateTime.ofInstant(closeTime.toInstant(), ZoneId.systemDefault()).minusDays(2).toInstant(ZoneOffset.UTC))
        }
        else {
            return null
        }

    }

    fun getLastDayFromEvent(eventData: EventData): Date? {

        val closeTime = getCloseFromEvent(eventData)

        if (closeTime != null) {
            return Date.from(LocalDateTime.ofInstant(closeTime.toInstant(), ZoneId.systemDefault()).minusDays(1).toInstant(ZoneOffset.UTC))
        }
        else {
            return null
        }

    }

    fun getOpenFromEvent(eventData: EventData): Date? {

        if (eventData.isQuiz()) {
            return Date(eventData.relatedquiz!!.getTimeOpen()!!)
        }

        if (eventData.isAssign()) {
            return Date(eventData.relatedassign!!.getAllowsubmissionsfromdate()!!)
        }

        return null

    }

}