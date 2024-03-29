// Plugin Klaza para Moodle - Server - TelegramInstanceController.kt
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
import klaza.klaza_server.dtos.TelegramInstanceDTO
import klaza.klaza_server.services.DiscordInstanceService
import klaza.klaza_server.services.TelegramInstanceService
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
@RequestMapping("/telegramInstance")
class TelegramInstanceController {

    @Autowired lateinit var telegramInstanceService: TelegramInstanceService

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TelegramInstanceController::class.java)
    }

    @GetMapping("/{id}")
    fun getTelegramInstanceById(@PathVariable id: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Get Telegram instance $id${Colors.RESET}")

        try {
            val instance = telegramInstanceService.getTelegramInstanceById(id)
            val dto = instance.toDTO(telegramInstanceService.getInstanceConfigDTO(id))

            LOGGER.info("${Colors.GREEN}Telegram instance $id found${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}Telegram instance $id DTO: $dto${Colors.RESET}")

            return ResponseEntity(dto, HttpStatus.OK)
        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error getting Discord instance $id ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @PostMapping("/create/{courseId}/{creatorId}")
    fun createTelegramInstance(@PathVariable courseId: Long, @PathVariable creatorId: Long, @RequestBody dto: TelegramInstanceDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Create Telegram instance: $dto${Colors.RESET}")

        try {

            telegramInstanceService.createTelegramInstance(dto, courseId, creatorId)

            return ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error creating Telegram instance ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping
    fun editDiscordInstance(@RequestBody dto: TelegramInstanceDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Edit Telegram instance: $dto${Colors.RESET}")

        try {
            telegramInstanceService.editTelegramInstance(dto)

            return ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error editing Telegram instance ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteDiscordInstance(@PathVariable id: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Delete Telegram instance $id${Colors.RESET}")

        try {
            telegramInstanceService.deleteTelegramInstance(id)

            return ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error deleting Telegram instance $id ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}