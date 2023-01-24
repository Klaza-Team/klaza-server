// Plugin Klaza para Moodle - Server - CourseController.kt
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

package klaza.klaza_server.controllers

import klaza.klaza_server.classes.Colors
import klaza.klaza_server.repositories.KlazaDiscordInstanceConfigRepository
import klaza.klaza_server.repositories.KlazaTelegramInstanceConfigRepository
import klaza.klaza_server.services.CourseService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CourseController {

    @Autowired lateinit var courseService: CourseService

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CourseController::class.java)
    }

    @GetMapping("/{id}")
    fun getCourse(@PathVariable id: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Get course $id ${Colors.RESET}")

        try {
            val course = courseService.getCourseById(id)
            val discordInstances = courseService.getCourseDiscordInstancesDTO(id)
            val telegramInstances = courseService.getCourseTelegramInstancesDTO(id)

            val dto = course.toDTO(discordInstances, telegramInstances)

            LOGGER.info("${Colors.GREEN}Course $id found ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}Course $id DTO: $dto ${Colors.RESET}")

            return ResponseEntity(dto, HttpStatus.OK)
        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error getting course $id ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

}