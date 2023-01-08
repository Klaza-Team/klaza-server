// Plugin Klaza para Moodle - Server - DiscordInstanceDTO.kt
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

import klaza.klaza_server.data.CourseData
import klaza.klaza_server.data.DiscordInstanceData

class DiscordInstanceDTO(userID: Long, courseData: CourseData) {

    private var user: List<UserCourseDiscordConfigDTO>
    private var other: List<UserCourseDiscordConfigDTO>

    init {
        user = courseData.getDiscordInstancesByUserID(userID).map { it.getConfigs().toDTO() }
        other = courseData.getDiscordInstancesByNotUserID(userID).map { it.getConfigs().toDTO() }
    }

    override fun toString(): String {
        return "DiscordInstanceDTO(" +
                "user=$user, " +
                "other=$other" +
                ")"
    }

}