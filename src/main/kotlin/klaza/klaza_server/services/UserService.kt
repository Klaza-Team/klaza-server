// Plugin Klaza para Moodle - Server - UserService.kt
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

import klaza.klaza_server.dtos.UserCourseConfigDTO
import klaza.klaza_server.dtos.UserGlobalConfigDTO
import klaza.klaza_server.dtos.UserNotificationAppDTO
import klaza.klaza_server.dtos.UserNotificationContactDTO
import klaza.klaza_server.models.*
import klaza.klaza_server.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.min

@Service
class UserService {

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var userInfoDataRepository: UserInfoDataRepository
    @Autowired lateinit var discordAccountModelRepository: KlazaDiscordAccountModelRepository
    @Autowired lateinit var telegramAccountModelRepository: KlazaTelegramAccountModelRepository
    @Autowired lateinit var whatsappAccountModelRepository: KlazaWhatsappAccountModelRepository
    @Autowired lateinit var klazaUserInstanceConfigRepository: KlazaUserInstanceConfigRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var klazaGlobalConfigRepository: KlazaGlobalConfigRepository
    @Autowired lateinit var klazaUserInstanceRepository: KlazaUserInstanceRepository


    fun getUserById(userId: Long): UserModel {
        return userRepository.findById(userId).orElseThrow { Exception("User not found") }
    }

    fun getUsers(): List<UserModel> {
        return userRepository.findAll()
    }

    fun getUserNotificationContacts(userId: Long): List<UserNotificationContactDTO> {

        val returnList = mutableListOf<UserNotificationContactDTO>()
        val user = getUserById(userId)

        val discordAccont = discordAccountModelRepository.findByUserId(userId)
        val discord = if (discordAccont != null) discordAccont.value else null
        val discordPriority = if (discordAccont != null) discordAccont.priority else -1

        val whatsappAccount = whatsappAccountModelRepository.findByUserId(userId)
        val whatsapp = if (whatsappAccount != null) whatsappAccount.value else null
        val whatsappPriority = if (whatsappAccount != null) whatsappAccount.priority else -1

        val telegramAccount = telegramAccountModelRepository.findByUserId(userId)
        val telegram = if (telegramAccount != null) telegramAccount.value else null
        val telegramPriority = if (telegramAccount != null) telegramAccount.priority else -1

        val email = user.email!!
        val emailPriority = min(discordPriority!!, min(whatsappPriority!!, telegramPriority!!))-1

        if (discord != null) {
            returnList.add(UserNotificationContactDTO("DISCORD", discord, discordPriority))
        }
        if (whatsapp != null) {
            returnList.add(UserNotificationContactDTO("WHATSAPP", whatsapp, whatsappPriority))
        }
        if (telegram != null) {
            returnList.add(UserNotificationContactDTO("TELEGRAM", telegram, telegramPriority))
        }

        returnList.add(UserNotificationContactDTO("EMAIL", email, emailPriority))

        return returnList.sortedBy { it.priority }

    }

    fun getOnlyAllowedUserNotificationContacts(userId: Long): List<UserNotificationContactDTO> {

        val list = getUserNotificationContacts(userId).filter { it.priority > 0 }

        return list.ifEmpty { mutableListOf(UserNotificationContactDTO("EMAIL", userRepository.findById(userId).get().email!!, 0)) }

    }

    fun getUserNotificationPriority(userId: Long): List<UserNotificationAppDTO> {

        val list: MutableList<UserNotificationAppDTO> = mutableListOf()

        val discord: KlazaDiscordAccountModel? = discordAccountModelRepository.findByUserId(userId)
        val telegram: KlazaTelegramAccountModel? = telegramAccountModelRepository.findByUserId(userId)
        val whatsapp: KlazaWhatsappAccountModel? = whatsappAccountModelRepository.findByUserId(userId)

        if (discord != null) { list.add(discord.toDTO()) }
        if (telegram != null) { list.add(telegram.toDTO()) }
        if (whatsapp != null) { list.add(whatsapp.toDTO()) }

        return list.sortedBy { it.priority }

    }

    fun getUserCourseConfigs(userId: Long, courseId: Long): UserCourseConfigDTO {

        val config = klazaUserInstanceConfigRepository.findUserCourseConfigs(userId, courseId)!!

        return config.toDTO()

    }

    fun editUserCourseConfig(userId: Long, courseId: Long, configDTO: UserCourseConfigDTO) {

        val config = klazaUserInstanceConfigRepository.findUserCourseConfigs(userId, courseId)
        val user = userRepository.findById(userId).get()
        val course = courseRepository.findById(courseId).get()

        if (config == null) {

            val instance = klazaUserInstanceRepository.saveAndFlush(KlazaUserInstanceModel(null, course, user))
            val newConfig = KlazaUserInstanceConfigModel(null, instance)

            println(newConfig)

            klazaUserInstanceConfigRepository.save(newConfig)

        }
        else {
            config.useGlobal = configDTO.use_global ?: false
            config.notifyCreateContent = configDTO.notify_create_content
            config.notifyEditContent = configDTO.notify_edit_content
            config.notifyDeleteContent = configDTO.notify_delete_content
            config.notifyDeadline2Days = configDTO.notify_deadline_2_days
            config.notifyDeadline1Day = configDTO.notify_deadline_1_day
            config.notifyDeadline = configDTO.notify_deadline
            config.notifySendAssignment = configDTO.notify_send_assignment
            config.notifyReceiveMessage = configDTO.notify_receive_message
            config.notifyReceiveComment = configDTO.notify_receive_comment
            config.notifyDeleteComment = configDTO.notify_delete_comment

            klazaUserInstanceConfigRepository.save(config)
        }

    }

    fun getUserCourses(userId: Long): List<CourseModel> {
        return courseRepository.findAllByUserId(userId)
    }

    fun getUserGlobalConfig(userId: Long): UserGlobalConfigDTO {

        val config = klazaGlobalConfigRepository.findByUserId(userId)

        return config?.toDTO() ?: UserGlobalConfigDTO()

    }

    fun editUserGlobalConfig(userId: Long, config: UserGlobalConfigDTO) {

        val globalConfig = klazaGlobalConfigRepository.findByUserId(userId)
        val user = userRepository.findById(userId).get()

        println("$globalConfig $user")

        if (globalConfig == null) {

            val config = KlazaGlobalConfigModel(
                null,
                user,
                config.notify_create_content,
                config.notify_edit_content,
                config.notify_delete_content,
                config.notify_deadline_2_days,
                config.notify_deadline_1_day,
                config.notify_deadline,
                config.notify_send_assignment,
                config.notify_receive_message,
                config.notify_receive_comment,
                config.notify_delete_comment,
            )

            println(config)

            klazaGlobalConfigRepository.save(config)
        } else {

            globalConfig.notifyCreateContent = config.notify_create_content
            globalConfig.notifyEditContent = config.notify_edit_content
            globalConfig.notifyDeleteContent = config.notify_delete_content
            globalConfig.notifyDeadline2Days = config.notify_deadline_2_days
            globalConfig.notifyDeadline1Day = config.notify_deadline_1_day
            globalConfig.notifyDeadline = config.notify_deadline
            globalConfig.notifySendAssignment = config.notify_send_assignment
            globalConfig.notifyReceiveMessage = config.notify_receive_message
            globalConfig.notifyReceiveComment = config.notify_receive_comment
            globalConfig.notifyDeleteComment = config.notify_delete_comment

            klazaGlobalConfigRepository.save(globalConfig)
        }

    }

    fun editUserDiscordAccount(userId: Long, account: UserNotificationAppDTO) {

        val discordAccount = discordAccountModelRepository.findByUserId(userId)
        val user = this.getUserById(userId)

        if (discordAccount == null) {

            val newAccount = KlazaDiscordAccountModel(null, user, account.value, account.priority)

            discordAccountModelRepository.save(newAccount)

        }
        else {
            discordAccount.user = user
            discordAccount.priority = account.priority
            discordAccount.value = account.value

            discordAccountModelRepository.save(discordAccount)
        }
    }

    fun editUserTelegramAccount(userId: Long, account: UserNotificationAppDTO) {

        val telegramAccount = telegramAccountModelRepository.findByUserId(userId)
        val user = this.getUserById(userId)

        if (telegramAccount == null) {

            val newAccount = KlazaTelegramAccountModel(null, user, account.value, account.priority)

            telegramAccountModelRepository.save(newAccount)

        }
        else {
            telegramAccount.user = user
            telegramAccount.priority = account.priority
            telegramAccount.value = account.value

            telegramAccountModelRepository.save(telegramAccount)
        }
    }

    fun editUserWhatsappAccount(userId: Long, account: UserNotificationAppDTO) {

        val whatsappAccount = whatsappAccountModelRepository.findByUserId(userId)
        val user = this.getUserById(userId)

        if (whatsappAccount == null) {

            val newAccount = KlazaWhatsappAccountModel(null, user, account.value, account.priority)

            whatsappAccountModelRepository.save(newAccount)

        }
        else {
            whatsappAccount.user = user
            whatsappAccount.priority = account.priority
            whatsappAccount.value = account.value

            whatsappAccountModelRepository.save(whatsappAccount)
        }
    }

}