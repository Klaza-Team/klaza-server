// Plugin Klaza para Moodle - Server - UserController.kt
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
import klaza.klaza_server.dtos.UserDTO
import klaza.klaza_server.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class CourseController {

    @Autowired private lateinit var context: ApplicationContext
    @Autowired lateinit var userService: UserService

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CourseController::class.java)
    }

    @PostMapping("/login")
    fun login() {
        LOGGER.info("Login")
    }

   @GetMapping("/{id}")
   fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
       LOGGER.info("${Colors.YELLOW} Get user by id: $id ${Colors.RESET}")

       try {
           return ResponseEntity(userService.getUserById(id).toData(this.context).toDTO(), HttpStatus.OK)
       }
       catch (e: Exception) {
           LOGGER.error("${Colors.RED} Error getting user by id: $id ${Colors.RESET}")
           LOGGER.error("${Colors.RED} ${e.message} ${Colors.RESET}")
           return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
       }

   }

}