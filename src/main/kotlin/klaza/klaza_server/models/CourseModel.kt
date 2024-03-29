// Plugin Klaza para Moodle - Server - Course.kt
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

import klaza.klaza_server.dtos.CourseDTO
import klaza.klaza_server.dtos.DiscordInstanceDTO
import klaza.klaza_server.dtos.TelegramInstanceDTO
import org.springframework.context.ApplicationContext
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Table

@Entity
@Table(name = "mdl_course")
class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "fullname", length = 100, nullable = false)
    var fullname: String? = null

    @Column(name = "shortname", length = 100, nullable = false)
    var shortname: String? = null

    fun toDTO(discordIntances: List<DiscordInstanceDTO>, telegramIntances: List<TelegramInstanceDTO>): CourseDTO { return CourseDTO(this, discordIntances, telegramIntances) }

    override fun toString(): String {
        return "Course(id=$id, fullname=$fullname, shortname=$shortname)"
    }

}