// Plugin Klaza para Moodle - Server - KlazaDiscordInstanceRepository.kt
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

package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaDiscordInstanceModel
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaDiscordInstanceRepository : JpaRepository<KlazaDiscordInstanceModel, Long> {

    fun findAllByGuild(guild: String): List<KlazaDiscordInstanceModel>

    fun findAllByChannel(channel: String): List<KlazaDiscordInstanceModel>

    fun findAllByGuildAndChannel(guild: String, channel: String): List<KlazaDiscordInstanceModel>

    fun findAllByCourse_Id(courseId: Long): List<KlazaDiscordInstanceModel>

}