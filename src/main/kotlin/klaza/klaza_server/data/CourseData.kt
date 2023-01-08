// Plugin Klaza para Moodle - Server - CouseData.kt
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

import klaza.klaza_server.dtos.CourseDTO
import klaza.klaza_server.models.CourseModel
import klaza.klaza_server.repositories.KlazaDiscordInstanceRepository
import klaza.klaza_server.repositories.KlazaTelegramInstanceRepository
import klaza.klaza_server.repositories.KlazaUserInstanceConfigRepository
import klaza.klaza_server.services.ImageService
import org.springframework.beans.factory.annotation.Autowired

class CourseData(private var courseModel: CourseModel) {

    @Autowired lateinit var imageService: ImageService
    @Autowired lateinit var klazaUserInstanceConfigRepository: KlazaUserInstanceConfigRepository
    @Autowired lateinit var klazaDiscordInstanceRepository: KlazaDiscordInstanceRepository
    @Autowired lateinit var klazaTelegramInstanceRepository: KlazaTelegramInstanceRepository

    private var id: Long = courseModel.id!!
    private var fullname: String = courseModel.fullname!!
    private var shortname: String = courseModel.shortname!!
    private var image: String = imageService.getRandomCourseImage()

    private var userConfigs: List<CourseUserConfigData>? = null

    private var discordIntances: List<DiscordInstanceData>? = null
    private var telegramIntances: List<TelegramInstanceData>? = null

    fun getID(): Long { return this.id }
    fun getFullname(): String { return this.fullname }
    fun getShortname(): String { return this.shortname }
    fun getImage(): String { return this.image }
    fun getUserConfigs(): List<CourseUserConfigData> {

        if (this.userConfigs == null) {
            this.userConfigs = klazaUserInstanceConfigRepository.findAllByUserInstanceId_CourseId(this.getID()).map { it -> it.toData() }
        }

        return this.userConfigs!!

    }
    fun getUserConfigsByUserID(userID: Long): CourseUserConfigData? { return this.getUserConfigs().find { it.getUser().getId() == userID } }
    fun getDiscordInstances(): List<DiscordInstanceData> {

        if (this.discordIntances == null) {
            this.discordIntances = klazaDiscordInstanceRepository.findAllByCourse_Id(this.getID()).map { it.toData() }
        }

        return this.discordIntances!!

    }
    fun getDiscordInstancesByUserID(userID: Long): List<DiscordInstanceData> { return this.getDiscordInstances().filter { it.getCreator().getId() == userID } }
    fun getDiscordInstancesByNotUserID(userID: Long): List<DiscordInstanceData> { return this.getDiscordInstances().filter { it.getCreator().getId() != userID } }
    fun getTelegramInstances(): List<TelegramInstanceData> {

        if (this.telegramIntances == null) {
            this.telegramIntances = klazaTelegramInstanceRepository.findAllByCourse_Id(this.getID()).map { it.toData() }
        }

        return this.telegramIntances!!

    }
    fun getTelegramInstancesByUserID(userID: Long): List<TelegramInstanceData> { return this.getTelegramInstances().filter { it.getCreator().getId() == userID } }
    fun getTelegramInstancesByNotUserID(userID: Long): List<TelegramInstanceData> { return this.getTelegramInstances().filter { it.getCreator().getId() != userID } }

    fun toDTO(userID: Long): CourseDTO { return CourseDTO(userID, this) }

}