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

class EventDTO {

    lateinit var eventname: String
    lateinit var objectid: String
    lateinit var crud: String
    var contextlevel: Long? = null
    var contextid: Long? = null
    var userid: Long? = null
    var courseid: Long? = null
    var relateduserid: Long? = null
    lateinit var action: String
    lateinit var target: String
    lateinit var other: Any

    override fun toString(): String {
        return "EventDTO(eventname=${eventname}, objectid=${objectid}, crud=${crud}, contextlevel=${contextlevel}, contextid=${contextid}, userid=${userid}, courseid=${courseid}, relateduserid=${relateduserid}, action=${action}, target=${target}, other=${other}"
    }

}