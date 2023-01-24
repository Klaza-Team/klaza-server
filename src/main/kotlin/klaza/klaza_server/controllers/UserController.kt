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
import klaza.klaza_server.dtos.UserCourseConfigDTO
import klaza.klaza_server.dtos.UserGlobalConfigDTO
import klaza.klaza_server.dtos.UserNotificationAppDTO
import klaza.klaza_server.services.CourseService
import klaza.klaza_server.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var courseService: CourseService

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }

    @PostMapping("/login")
    fun login() {
        LOGGER.info("Login")
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Get user $id ${Colors.RESET}")

        try {

            val user = userService.getUserById(id)
            val dto = user.toDTO(userService.getUserNotificationPriority(id), userService.getUserGlobalConfig(id))

            LOGGER.info("${Colors.GREEN}User $id found ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id DTO: $dto ${Colors.RESET}")

            return ResponseEntity(dto, HttpStatus.OK)

        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error getting user $id ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @GetMapping("/{id}/course_configs/{course}")
    fun getUserCourseConfigs(@PathVariable id: Long, @PathVariable course: Long): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Get user $id course config $course ${Colors.RESET}")

        try {

            val configs = userService.getUserCourseConfigs(id, course)

            LOGGER.info("${Colors.GREEN}User $id course configs $course found ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id course configs $course DTO: $configs ${Colors.RESET}")

            return ResponseEntity(configs, HttpStatus.OK)

        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error getting user $id course configs $course ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @PutMapping("/{id}/course_configs/{course}")
    fun updateUserCourseConfigs(@PathVariable id: Long, @PathVariable course: Long, @RequestBody configs: UserCourseConfigDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Update user $id course config $course ${Colors.RESET}")

        try {

            userService.editUserCourseConfig(id, course, configs)

            LOGGER.info("${Colors.GREEN}User $id course configs $course updated ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id course configs $course DTO: $configs ${Colors.RESET}")

            return ResponseEntity(HttpStatus.OK)

        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error updating user $id course configs $course ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}/courses")
    fun getUserCourses(@PathVariable id: Long): ResponseEntity<Any> {

        LOGGER.info("${Colors.YELLOW}Get user $id courses ${Colors.RESET}")

        try {

            val courses = userService.getUserCourses(id).map { it.toDTO(courseService.getCourseDiscordInstancesDTO(it.id!!), courseService.getCourseTelegramInstancesDTO(it.id!!)) }

            LOGGER.info("${Colors.GREEN}User $id courses found ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id courses DTO: $courses ${Colors.RESET}")

            return ResponseEntity(courses, HttpStatus.OK)

        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error getting user $id courses ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @PutMapping("/{id}/global_config")
    fun editUserGlobalConfig(@PathVariable id: Long, @RequestBody dto: UserGlobalConfigDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Edit user $id global config ${Colors.RESET}")

        try {

            userService.editUserGlobalConfig(id, dto)

            LOGGER.info("${Colors.GREEN}User $id global config edited ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id global config DTO: $dto ${Colors.RESET}")

            return ResponseEntity(HttpStatus.OK)

        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error editing user $id global config ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/{id}/discordAccount")
    fun editUserDiscordAccount(@PathVariable id: Long, @RequestBody dto: UserNotificationAppDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Edit user $id discord account ${Colors.RESET}")

        try {

            userService.editUserDiscordAccount(id, dto)

            LOGGER.info("${Colors.GREEN}User $id discord account edited ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id discord account DTO: $dto ${Colors.RESET}")

            return ResponseEntity(HttpStatus.OK)

        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error editing user $id discord account ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/{id}/telegramAccount")
    fun editUserTelegramAccount(@PathVariable id: Long, @RequestBody dto: UserNotificationAppDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Edit user $id telegram account ${Colors.RESET}")

        try {

            userService.editUserTelegramAccount(id, dto)

            LOGGER.info("${Colors.GREEN}User $id telegram account edited ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id telegram account DTO: $dto ${Colors.RESET}")

            return ResponseEntity(HttpStatus.OK)

        }
        catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error editing user $id telegram account ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/{id}/whatsappAccount")
    fun editUserWhatsappAccount(@PathVariable id: Long, @RequestBody dto: UserNotificationAppDTO): ResponseEntity<Any> {
        LOGGER.info("${Colors.YELLOW}Edit user $id whatsapp account ${Colors.RESET}")

        try {

            userService.editUserWhatsappAccount(id, dto)

            LOGGER.info("${Colors.GREEN}User $id whatsapp account edited ${Colors.RESET}")
            LOGGER.info("${Colors.GREEN}User $id whatsapp account DTO: $dto ${Colors.RESET}")

            return ResponseEntity(HttpStatus.OK)

        } catch (e: Exception) {
            LOGGER.error("${Colors.RED} Error editing user $id whatsapp account ${Colors.RESET}: $e")
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}