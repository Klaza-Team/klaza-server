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

import klaza.klaza_server.dtos.UserNotificationContactDTO
import klaza.klaza_server.repositories.UserInfoDataRepository
import klaza.klaza_server.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.min

@Service
class UserService {

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var userInfoDataRepository: UserInfoDataRepository

    fun getUserNotificationContacts(userId: Long): List<UserNotificationContactDTO> {

        val userInfoData = userInfoDataRepository.findAllKlazaUserInfoDataByUserId(userId)

        if (userInfoData.isEmpty()) {
            return listOf()
        }

        val user = userInfoData[0].user!!

        val discord = userInfoData.find { value -> value.field!!.shortname == "klaza_discord" }!!.data!!
        val discordPriority = userInfoData.find { value -> value.field!!.shortname == "klaza_discord_priority" }!!.data!!.toInt()

        val whatsapp = userInfoData.find { value -> value.field!!.shortname == "klaza_whatsapp" }!!.data!!
        val whatsappPriority = userInfoData.find { value -> value.field!!.shortname == "klaza_whatsapp_priority" }!!.data!!.toInt()

        val telegram = userInfoData.find { value -> value.field!!.shortname == "klaza_telegram" }!!.data!!
        val telegramPriority = userInfoData.find { value -> value.field!!.shortname == "klaza_telegram_priority" }!!.data!!.toInt()

        val email = user.email!!
        val emailPriority = min(discordPriority, min(whatsappPriority, telegramPriority))-1

        return mutableListOf<UserNotificationContactDTO>(
            UserNotificationContactDTO("DISCORD", discord, discordPriority),
            UserNotificationContactDTO("WHATSAPP", whatsapp, whatsappPriority),
            UserNotificationContactDTO("TELEGRAM", telegram, telegramPriority),
            UserNotificationContactDTO("EMAIL", email, emailPriority)
        ).sortedBy { it.priority }

    }

    fun getOnlyAllowedUserNotificationContacts(userId: Long): List<UserNotificationContactDTO> {

        val list = getUserNotificationContacts(userId).filter { it.priority > 0 }

        return list.ifEmpty { mutableListOf(UserNotificationContactDTO("EMAIL", userRepository.findById(userId).get().email!!, 0)) }

    }

}