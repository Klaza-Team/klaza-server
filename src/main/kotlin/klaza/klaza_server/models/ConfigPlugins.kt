// Plugin Klaza para Moodle - Server - ConfigPlugins.kt
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

package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_config_plugins")
class ConfigPlugins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "plugin", length = 100, nullable = false)
    private var plugin: String? = null

    @Column(name = "name", length = 100, nullable = false)
    private var name: String? = null

    @Column(name = "value", columnDefinition = "LONGTEXT", nullable = false)
    private var value: String? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getPlugin(): String? {
        return plugin
    }

    fun setPlugin(plugin: String?) {
        this.plugin = plugin
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getValue(): String? {
        return value
    }

    fun setValue(value: String?) {
        this.value = value
    }

    override fun toString(): String {
        return "ConfigPlugins(id=$id, plugin=$plugin, name=$name, value=$value)"
    }

}