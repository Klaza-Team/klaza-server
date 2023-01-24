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

import klaza.klaza_server.models.CourseModel
import klaza.klaza_server.models.KlazaDiscordInstanceModel
import java.util.Base64

class DiscordInstanceDTO {

    var id: Long
    var guild_id: String
    var channel_id: String
    var config: UserCourseConfigDTO
    var creator_id: Long

    constructor() {
        this.id = 0
        this.guild_id = ""
        this.channel_id = ""
        this.config = UserCourseConfigDTO()
        this.creator_id = 0
    }

    constructor(id: Long, guild_id: String, channel_id: String, config: UserCourseConfigDTO, creator_id: Long) {
        this.id = id
        this.guild_id = guild_id
        this.channel_id = channel_id
        this.config = config
        this.creator_id = creator_id
    }

    constructor(klazaDiscordInstanceModel: KlazaDiscordInstanceModel, config: UserCourseConfigDTO) {
        this.id = klazaDiscordInstanceModel.id!!
        this.guild_id = klazaDiscordInstanceModel.guild!!
        this.channel_id = klazaDiscordInstanceModel.channel!!
        this.config = config
        this.creator_id = klazaDiscordInstanceModel.creator!!.id!!
    }

    override fun toString(): String {
        return "DiscordInstanceDTO(id=$id, guild_id='$guild_id', channel_id='$channel_id', config=$config, creator_id=$creator_id)"
    }

}