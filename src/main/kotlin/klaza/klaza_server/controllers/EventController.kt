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
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/event")
open class EventController {

    // \core\event\course_module_created
    // \core\event\course_module_updated
    // \core\event\course_module_deleted

    // \mod_chat\event\message_sent

    // \assignsubmission_file\event\submission_updated
    // \assignsubmission_onlinetext\event\assessable_uploaded

    // \mod_quiz\event\attempt_submitted

    // \core\event\comment_created
    // \core\event\comment_deleted

    companion object {
        private val LOGGER = LoggerFactory.getLogger(EventController::class.java)
    }

    @PostMapping("/course_module_created")
    fun courseModuleCreated(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "course_module_created -> ${body.toString()}" + Colors.RESET)
    }

    @PostMapping("/course_module_updated")
    fun courseModuleUpdated(@RequestBody body: EventDTO) {
        LOGGER.info(Colors.GREEN + "course_module_updated -> ${body.toString()}" + Colors.RESET)
    }

    @PostMapping("/test")
    fun test(@RequestBody body: Any) {

        LOGGER.info(Colors.GREEN_BOLD + "Ta testado -> ${body.toString()}" + Colors.RESET)

    }

}