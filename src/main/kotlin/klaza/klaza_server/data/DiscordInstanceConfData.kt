// Plugin Klaza para Moodle - Server - DiscordInstanceConfigData.kt
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
import klaza.klaza_server.models.KlazaDiscordInstanceConfigModel

class DiscordInstanceConfData(private var klazaDiscordInstanceConfigModel: KlazaDiscordInstanceConfigModel) {

    private var id: Long = klazaDiscordInstanceConfigModel.id!!
    private var instance: DiscordInstanceData? = null
    private var guild: String? = null
    private var channel: String? = null
    private var creator: UserData? = null
    private var config: ConfigData? = null

    fun getId(): Long { return this.id }
    fun getInstance(): DiscordInstanceData {

        if (this.instance == null) {
            this.instance = this.klazaDiscordInstanceConfigModel.discordInstance!!.toData()
        }

        return this.instance!!

    }
    fun getGuild(): String {

        if (this.guild == null) {
            this.guild = this.getInstance().getGuild()
        }

        return this.guild!!

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
            this.config = ConfigData(this.klazaDiscordInstanceConfigModel)
        }

        return this.config!!

    }

    fun toDTO(): UserCourseDiscordConfigDTO { return UserCourseDiscordConfigDTO(this) }

}