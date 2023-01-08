// Plugin Klaza para Moodle - Server - TelegramInstanceData.kt
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
import klaza.klaza_server.dtos.TelegramInstanceDTO
import klaza.klaza_server.models.KlazaDiscordInstanceModel
import klaza.klaza_server.models.KlazaTelegramInstanceModel


class TelegramInstanceData(private var klazaTelegramInstanceModel: KlazaTelegramInstanceModel) {

    private var id: Long = klazaTelegramInstanceModel.id!!
    private var channel: String = klazaTelegramInstanceModel.channel!!
    private var course: CourseData? = null
    private var creator: UserData? = null
    private var configs: TelegramInstanceConfData? = null

    fun getId(): Long { return this.id }
    fun getChannel(): String { return this.channel }
    fun getCourse(): CourseData {

        if (this.course == null) {
            this.course = klazaTelegramInstanceModel.course!!.toData()
        }

        return this.course!!

    }
    fun getCreator(): UserData {

        if (this.creator == null) {
            this.creator = klazaTelegramInstanceModel.creator!!.toData()
        }

        return this.creator!!

    }
    fun getConfigs(): TelegramInstanceConfData {

        if (this.configs == null) {
            this.configs = klazaTelegramInstanceModel.configs!!.toData()
        }

        return this.configs!!

    }

    fun toDTO(): TelegramInstanceDTO { return TelegramInstanceDTO(this.getCreator().getId(), this.getCourse()) }

}