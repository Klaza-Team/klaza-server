// Plugin Klaza para Moodle - Server - DiscordInstanceController.kt
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
import klaza.klaza_server.dtos.DiscordInstanceDTO
import klaza.klaza_server.services.DiscordInstanceService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/discordInstance")
class DiscordInstanceController {

    @Autowired lateinit var discordInstanceService: DiscordInstanceService

    companion object {
        private val LOGGER = LoggerFactory.getLogger(DiscordInstanceController::class.java)
    }

    @GetMapping("/{id}")
    fun getDiscordInstanceById(@PathVariable id: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Get Discord instance $id${Colors.RESET}")

        try {
            val instance = discordInstanceService.getDiscordInstanceById(id)
            val dto = instance.toDTO(discordInstanceService.getInstanceConfigDTO(id))

            LOGGER.info("${Colors.GREEN}Discord instance $id found${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}Discord instance $id DTO: $dto${Colors.RESET}")

            return ResponseEntity(dto, HttpStatus.OK)
        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error getting Discord instance $id ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @PostMapping("/create/{courseId}/{creatorId}")
    fun createDiscordInstance(@PathVariable courseId: Long, @PathVariable creatorId: Long, @RequestBody dto: DiscordInstanceDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Create Discord instance: $dto${Colors.RESET}")

        try {

            discordInstanceService.createDiscordInstance(dto, courseId, creatorId)

            return ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error creating Discord instance ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping
    fun editDiscordInstance(@RequestBody dto: DiscordInstanceDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Edit Discord instance: $dto${Colors.RESET}")

        try {
            discordInstanceService.editDiscordInstance(dto)

            return ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error editing Discord instance ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteDiscordInstance(@PathVariable id: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Delete Discord instance $id${Colors.RESET}")

        try {
            discordInstanceService.deleteDiscordInstance(id)

            return ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error deleting Discord instance $id ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}