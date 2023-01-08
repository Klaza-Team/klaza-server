// Plugin Klaza para Moodle - Server - DiscordInstanceData.kt
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

import klaza.klaza_server.dtos.DiscordInstanceDTO
import klaza.klaza_server.models.KlazaDiscordInstanceModel


class DiscordInstanceData(private var klazaDiscordInstanceModel: KlazaDiscordInstanceModel) {

    private var id: Long = klazaDiscordInstanceModel.id!!
    private var guild: String = klazaDiscordInstanceModel.guild!!
    private var channel: String = klazaDiscordInstanceModel.channel!!
    private var course: CourseData? = null
    private var creator: UserData? = null
    private var configs: DiscordInstanceConfData? = null

    fun getId(): Long { return this.id }
    fun getGuild(): String { return this.guild }
    fun getChannel(): String { return this.channel }
    fun getCourse(): CourseData {

        if (this.course == null) {
            this.course = klazaDiscordInstanceModel.course!!.toData()
        }

        return this.course!!

    }
    fun getCreator(): UserData {

        if (this.creator == null) {
            this.creator = klazaDiscordInstanceModel.creator!!.toData()
        }

        return this.creator!!

    }
    fun getConfigs(): DiscordInstanceConfData {

        if (this.configs == null) {
            this.configs = klazaDiscordInstanceModel.configs!!.toData()
        }

        return this.configs!!

    }

    fun toDTO(): DiscordInstanceDTO { return DiscordInstanceDTO(this.getCreator().getId(), this.getCourse()) }

}