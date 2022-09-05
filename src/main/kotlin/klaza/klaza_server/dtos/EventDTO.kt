// Plugin Klaza para Moodle - Server - EventDTO.kt
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

package klaza.klaza_server.dtos

import klaza.klaza_server.data.EventData
import klaza.klaza_server.data.EventOtherData
import klaza.klaza_server.repositories.AssignRepository
import klaza.klaza_server.repositories.CourseRepository
import klaza.klaza_server.repositories.QuizRepository
import klaza.klaza_server.repositories.UserRepository

class EventDTO(
    var eventname: String,
    var objectid: String,
    var crud: String,
    var contextlevel: Long,
    var contextid: Long,
    var userid: Long,
    var courseid: Long,
    var relateduserid: Long,
    var action: String,
    var target: String,
    var other: EventOtherData) {

    fun convertToData(userRepository: UserRepository, courseRepository: CourseRepository, assignRepository: AssignRepository, quizRepository: QuizRepository): EventData {
        return EventData(
            eventname = eventname,
            objectid = objectid,
            crud = crud,
            contextlevel = contextlevel,
            contextid = contextid,
            user = if (userid != 0L) userRepository.findById(userid).get() else null,
            course = if (courseid != 0L) courseRepository.findById(courseid).get() else null,
            relateduser = if (relateduserid != 0L) userRepository.findById(relateduserid).get() else null,
            action = action,
            target = target,
            other = other,
            relatedassign = if (other.modulename == "assign") assignRepository.findById(other.instanceid).get() else null,
            relatedquiz = if (other.modulename == "quiz") quizRepository.findById(other.instanceid).get() else null
        )
    }

    override fun toString(): String {
        return "EventDTO(eventname=${eventname}, objectid=${objectid}, crud=${crud}, contextlevel=${contextlevel}, contextid=${contextid}, userid=${userid}, courseid=${courseid}, relateduserid=${relateduserid}, action=${action}, target=${target}, other=${other}"
    }

}