// Plugin Klaza para Moodle - Server - CourseService.kt
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

package klaza.klaza_server.services

import klaza.klaza_server.dtos.DiscordInstanceDTO
import klaza.klaza_server.dtos.TelegramInstanceDTO
import klaza.klaza_server.dtos.UserCourseConfigDTO
import klaza.klaza_server.models.CourseModel
import klaza.klaza_server.models.KlazaDiscordInstanceModel
import klaza.klaza_server.models.KlazaTelegramInstanceModel
import klaza.klaza_server.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseService {

    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var discordInstanceRepository: KlazaDiscordInstanceRepository
    @Autowired lateinit var telegramInstanceRepository: KlazaTelegramInstanceRepository
    @Autowired lateinit var klazaDiscordInstanceConfigRepository: KlazaDiscordInstanceConfigRepository
    @Autowired lateinit var klazaTelegramInstanceConfigRepository: KlazaTelegramInstanceConfigRepository

    fun getCourseById(courseId: Long): CourseModel {
        return courseRepository.findById(courseId).orElseThrow { Exception("Course not found") }
    }

    fun getCourses(): List<CourseModel> { return courseRepository.findAll() }

    fun getUserCourses(userId: Long): List<CourseModel> { return courseRepository.findAllByUserId(userId) }

    fun getCourseDiscordInstances(courseId: Long): List<KlazaDiscordInstanceModel> { return discordInstanceRepository.findAllByCourse_Id(courseId) }

    fun getCourseDiscordInstancesDTO(courseId: Long): List<DiscordInstanceDTO> {

        return this.getCourseDiscordInstances(courseId).map { instance ->
            val configs = klazaDiscordInstanceConfigRepository.findByDiscordInstance_Id(instance.id!!)
            instance.toDTO(UserCourseConfigDTO(configs!!))
        }

    }

    fun getCourseTelegramInstances(courseId: Long): List<KlazaTelegramInstanceModel> { return telegramInstanceRepository.findAllByCourse_Id(courseId) }

    fun getCourseTelegramInstancesDTO(courseId: Long): List<TelegramInstanceDTO> {

        return this.getCourseTelegramInstances(courseId).map { instance ->
            val configs = klazaTelegramInstanceConfigRepository.findByTelegramInstance_Id(instance.id!!)
            instance.toDTO(UserCourseConfigDTO(configs!!))
        }

    }

}