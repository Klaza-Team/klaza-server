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

import klaza.klaza_server.models.KlazaDiscordInstanceModel
import klaza.klaza_server.models.KlazaTelegramInstanceModel

class TelegramInstanceDTO {

    var id: Long
    var channel_id: String
    var config: UserCourseConfigDTO
    var creator_id: Long

    constructor(id: Long, channel_id: String, config: UserCourseConfigDTO, creator_id: Long) {
        this.id = id
        this.channel_id = channel_id
        this.config = config
        this.creator_id = creator_id
    }

    constructor(klazaTelegramInstanceModel: KlazaTelegramInstanceModel, config: UserCourseConfigDTO) {
        this.id = klazaTelegramInstanceModel.id!!
        this.channel_id = klazaTelegramInstanceModel.channel!!
        this.config = config
        this.creator_id = klazaTelegramInstanceModel.creator!!.id!!
    }

    override fun toString(): String {
        return "TelegramInstanceDTO(id=$id, channel_id='$channel_id', config=$config, creator_id=$creator_id)"
    }

}