// Plugin Klaza para Moodle - Server - ConfigPluginRepository.kt
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

import klaza.klaza_server.models.ConfigPluginsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConfigPluginsRepository : JpaRepository<ConfigPluginsModel, Long> {

    fun findAllByPlugin(plugin: String): List<ConfigPluginsModel>

    fun findAllByName(name: String): List<ConfigPluginsModel>

    fun findByPluginAndName(plugin: String, name: String): List<ConfigPluginsModel>

    @Query( "SELECT c " +
            "FROM ConfigPluginsModel c " +
            "WHERE c.plugin = 'local_klaza' AND c.name = ?1")
    fun findKlazaConfigByName(name: String): ConfigPluginsModel

    @Query( "SELECT c " +
            "FROM ConfigPluginsModel c " +
            "WHERE c.plugin = 'local_klaza'")
    fun findAllKlazaConfig(): List<ConfigPluginsModel>

    @Query( "SELECT c " +
            "FROM ConfigPluginsModel c " +
            "WHERE c.plugin = 'local_klaza' AND c.name = 'server_auth'")
    fun findKlazaConfigAuth(): ConfigPluginsModel

}