// Plugin Klaza para Moodle - Server - CourseDTO.kt
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
import java.util.Base64

class CourseDTO {

    var id: Long;
    var fullName: String
    var shortName: String
    var image: String
    var discordIntances: List<DiscordInstanceDTO>
    var telegramIntances: List<TelegramInstanceDTO>

    constructor(id: Long, fullName: String, shortName: String, image: String?, discordIntances: List<DiscordInstanceDTO>, telegramIntances: List<TelegramInstanceDTO>) {
        this.id = id
        this.fullName = fullName
        this.shortName = shortName
        this.image = image ?: this.getRandomImage()
        this.discordIntances = discordIntances
        this.telegramIntances = telegramIntances
    }

    constructor(courseModel: CourseModel, discordIntances: List<DiscordInstanceDTO>, telegramIntances: List<TelegramInstanceDTO>) {
        this.id = courseModel.id!!
        this.fullName = courseModel.fullname!!
        this.shortName = courseModel.shortname!!
        this.image = this.getRandomImage()
        this.discordIntances = discordIntances
        this.telegramIntances = telegramIntances
    }

    private fun getRandomImage(): String {
        return Base64.getEncoder().encodeToString(CourseDTO::class.java.getResource("/images/fundo_curso${(1..4).random()}.svg")?.readBytes())
    }

    override fun toString(): String {
        return "CourseDTO(id=$id, fullName='$fullName', shortName='$shortName', image='$image')"
    }

}