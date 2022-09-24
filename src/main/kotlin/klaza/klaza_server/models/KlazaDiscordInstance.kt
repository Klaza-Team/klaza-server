// Plugin Klaza para Moodle - Server - KlazaDiscordInstance.kt
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
@Table(name = "mdl_klaza_discord_instance")
class KlazaDiscordInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "guild", length = 100, nullable = false)
    var guild: String? = null

    @Column(name = "channel", length = 100, nullable = false)
    var channel: String? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    var course: Course? = null

    override fun toString(): String {
        return "KlazaDiscordInstance(id=$id, guild=$guild, channel=$channel, course=$course)"
    }

}