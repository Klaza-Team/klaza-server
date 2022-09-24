// Plugin Klaza para Moodle - Server - EventData.kt
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


package klaza.klaza_server.data

import klaza.klaza_server.dtos.EventDTO
import klaza.klaza_server.models.Assign
import klaza.klaza_server.models.Course
import klaza.klaza_server.models.Quiz
import klaza.klaza_server.models.User

class EventData(
    var eventname: String,
    var objectid: String,
    var crud: String,
    var contextlevel: Long,
    var contextid: Long,
    var user: User?,
    var course: Course?,
    var relateduser: User?,
    var action: String,
    var target: String,
    var other: EventOtherData,
    var relatedquiz: Quiz?,
    var relatedassign: Assign?) {

    fun convertToDTO(): EventDTO {
        return EventDTO(
            eventname = eventname,
            objectid = objectid,
            crud = crud,
            contextlevel = contextlevel,
            contextid = contextid,
            userid = if (user != null) user!!.id!! else 0,
            courseid = if (course != null) course!!.id!! else 0,
            relateduserid = if (relateduser != null) relateduser!!.id!! else 0,
            action = action,
            target = target,
            other = other
        )
    }

    fun isQuiz(): Boolean {
        return other.modulename == "quiz"
    }

    fun isAssign(): Boolean {
        return other.modulename == "assign"
    }

    override fun toString(): String {
        return "EventData(eventname='$eventname', objectid='$objectid', crud='$crud', contextlevel=$contextlevel, contextid=$contextid, user=$user, course=$course, relateduser=$relateduser, action='$action', target='$target', other=$other, relatedquiz=$relatedquiz, relatedassign=$relatedassign)"
    }

}