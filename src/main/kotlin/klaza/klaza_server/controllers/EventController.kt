// Plugin Klaza para Moodle - Server - EventController.kt
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

import klaza.klaza_server.dtos.EventDTO
import klaza.klaza_server.libs.Colors
import klaza.klaza_server.repositories.CourseRepository
import klaza.klaza_server.repositories.UserRepository
import klaza.klaza_server.services.CourseModuleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/event")
class EventController {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(EventController::class.java)
    }

    @Autowired lateinit var courseModuleService: CourseModuleService

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var courseRepository: CourseRepository

    // \core\event\course_module_created
    @PostMapping("/course_module_created")
    fun courseModuleCreated(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "course_module_created -> $body" + Colors.RESET)
        courseModuleService.created(body.convertToData(userRepository, courseRepository))
    }

    // \core\event\course_module_updated
    @PostMapping("/course_module_updated")
    fun courseModuleUpdated(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "course_module_updated -> $body" + Colors.RESET)
        courseModuleService.updated(body.convertToData(userRepository, courseRepository))
    }

    // \core\event\course_module_deleted
    @PostMapping("/course_module_deleted")
    fun courseModuleDeleted(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "course_module_deleted -> $body" + Colors.RESET)
        courseModuleService.deleted(body.convertToData(userRepository, courseRepository))
    }



    // \mod_chat\event\message_sent
    @PostMapping("/message_sent")
    fun messageSent(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "message_sent -> $body" + Colors.RESET)
    }



    // \assignsubmission_file\event\submission_updated
    @PostMapping("/submission_updated")
    fun submissionUpdated(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "submission_updated -> $body" + Colors.RESET)
    }

    // \assignsubmission_onlinetext\event\assessable_uploaded
    @PostMapping("/assessable_uploaded")
    fun assessableUploaded(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "assessable_uploaded -> $body" + Colors.RESET)
    }



    // \mod_quiz\event\attempt_submitted
    @PostMapping("/attempt_submitted")
    fun attemptSubmitted(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "attempt_submitted -> $body" + Colors.RESET)
    }



    // \core\event\comment_created
    @PostMapping("/comment_created")
    fun commentCreated(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "comment_created -> $body" + Colors.RESET)
    }

    // \core\event\comment_deleted
    @PostMapping("/comment_deleted")
    fun commentDeleted(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "comment_deleted -> $body" + Colors.RESET)
    }



    @PostMapping("/test")
    fun test(@RequestBody body: Any) {
        LOGGER.info(Colors.GREEN_BOLD + "Ta testado -> $body" + Colors.RESET)
    }

}