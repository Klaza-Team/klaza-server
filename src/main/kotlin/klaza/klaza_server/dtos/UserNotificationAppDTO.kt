// Plugin Klaza para Moodle - Server - UserNotificationAppDTO.kt
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
import klaza.klaza_server.models.KlazaDiscordAccountModel
import klaza.klaza_server.models.KlazaTelegramAccountModel
import klaza.klaza_server.models.KlazaWhatsappAccountModel
import java.util.Base64

class UserNotificationAppDTO {

    var id: Long
    var type: String
    var priority: Int
    var value: String

    constructor(id: Long, type: String, priority: Int, value: String) {
        this.id = id
        this.type = type
        this.priority = priority
        this.value = value
    }

    constructor(discordAccountModel: KlazaDiscordAccountModel) {
        this.id = discordAccountModel.id!!
        this.type = "discord"
        this.priority = discordAccountModel.priority!!
        this.value = discordAccountModel.value!!
    }

    constructor(telegramAccountModel: KlazaTelegramAccountModel) {
        this.id = telegramAccountModel.id!!
        this.type = "telegram"
        this.priority = telegramAccountModel.priority!!
        this.value = telegramAccountModel.value!!
    }

    constructor(whatsappAccountModel: KlazaWhatsappAccountModel) {
        this.id = whatsappAccountModel.id!!
        this.type = "whatsapp"
        this.priority = 1
        this.value = whatsappAccountModel.value!!
    }

    override fun toString(): String {
        return "UserNotificationAppDTO(id=$id, type='$type', priority=$priority, value='$value')"
    }

}