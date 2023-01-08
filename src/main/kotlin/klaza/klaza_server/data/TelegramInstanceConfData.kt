// Plugin Klaza para Moodle - Server - TelegramInstanceConfigData.kt
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

import klaza.klaza_server.dtos.UserCourseDiscordConfigDTO
import klaza.klaza_server.dtos.UserCourseTelegramConfigDTO
import klaza.klaza_server.models.KlazaDiscordInstanceConfigModel
import klaza.klaza_server.models.KlazaTelegramInstanceConfigModel

class TelegramInstanceConfData(private var telegramInstanceConfigModel: KlazaTelegramInstanceConfigModel) {

    private var id: Long = telegramInstanceConfigModel.id!!
    private var instance: TelegramInstanceData? = null
    private var channel: String? = null
    private var creator: UserData? = null
    private var config: ConfigData? = null

    fun getId(): Long { return this.id }
    fun getInstance(): TelegramInstanceData {

        if (this.instance == null) {
            this.instance = this.telegramInstanceConfigModel.telegramInstance!!.toData()
        }

        return this.instance!!

    }
    fun getChannel(): String {

        if (this.channel == null) {
            this.channel = this.getInstance().getChannel()
        }

        return this.channel!!

    }
    fun getCreator(): UserData {

        if (this.creator == null) {
            this.creator = this.getInstance().getCreator()
        }

        return this.creator!!

    }
    fun getConfig(): ConfigData {

        if (this.config == null) {
            this.config = ConfigData(this.telegramInstanceConfigModel)
        }

        return this.config!!

    }

    fun toDTO(): UserCourseTelegramConfigDTO { return UserCourseTelegramConfigDTO(this) }

}