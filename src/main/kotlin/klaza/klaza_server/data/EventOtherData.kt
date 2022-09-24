// Plugin Klaza para Moodle - Server - EventOtherData.kt
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

class EventOtherData(
    var modulename: String? = null,
    var instanceid: Long? = null,
    var name: String? = null,
    var courseid: Long? = null,
    var submissionid: Long? = null,
    var submissionattempt: Int? = null,
    var submissionstatus: String? = null,
    var filesubmissioncount: Int? = null,
    var groupid: Long? = null,
    var groupname: String? = null,
    var pathnamehashes: List<String> = listOf(),
    var content: String? = null,
    var format: String? = null) {

    override fun toString(): String {
        return "EventOtherData(modulename='$modulename', instanceid=$instanceid, name='$name', courseid=$courseid), submissionid=$submissionid, submissionattempt=$submissionattempt, submissionstatus=$submissionstatus, filesubmissioncount=$filesubmissioncount, groupid=$groupid, groupname=$groupname, pathnamehashes=$pathnamehashes, content=$content, format=$format)"
    }

}