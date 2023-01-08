// Plugin Klaza para Moodle - Server - CourseDTO.kt
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
import klaza.klaza_server.data.CourseUserConfigData
import klaza.klaza_server.data.DiscordInstanceData

class CourseDTO() {

    private var id: Long = 0
    private var fullname: String = ""
    private var shortname: String = ""
    private var image: String = ""
    private var actived: Boolean = false

    private var user_config: CourseUserConfigDTO? = null

    private var discordIntances: DiscordInstanceDTO? = null
    private var telegramIntances: TelegramInstanceDTO? = null

    constructor(userID: Long, courseData: CourseData) : this() {

        val userConfig = courseData.getUserConfigsByUserID(userID)

        this.id = courseData.getID()
        this.fullname = courseData.getFullname()
        this.shortname = courseData.getShortname()
        this.image = courseData.getImage()
        this.actived = userConfig != null
        this.user_config = if (this.actived) userConfig!!.toDTO() else null

        this.discordIntances = DiscordInstanceDTO(userID, courseData)
        this.telegramIntances = TelegramInstanceDTO(userID, courseData)

    }

    override fun toString(): String {
        return "CourseDTO(" +
                "id=$id, " +
                "fullname='$fullname', " +
                "shortname='$shortname', " +
                "image='$image', " +
                "actived=$actived, " +
                "user_config=$user_config, " +
                "discordIntances=$discordIntances, " +
                "telegramIntances=$telegramIntances" +
                ")"
    }

}