// Plugin Klaza para Moodle - Server - UserCourseTelegramConfigDTO.kt
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

import klaza.klaza_server.data.ConfigData
import klaza.klaza_server.data.TelegramInstanceConfData

class UserCourseTelegramConfigDTO(telegramInstanceConfData: TelegramInstanceConfData) {

    private var id: Long
    private var channel_id: String
    private var config: ConfigData
    private var creator: UserDTO

    init {
        this.id = telegramInstanceConfData.getId()
        this.channel_id = telegramInstanceConfData.getChannel()
        this.config = telegramInstanceConfData.getConfig()
        this.creator = telegramInstanceConfData.getCreator().toDTO()
    }

    override fun toString(): String {
        return "UserCourseTelegramConfigDTO(" +
                "id=$id, " +
                "channel_id='$channel_id', " +
                "config=$config, " +
                "creator=$creator" +
                ")"
    }

}