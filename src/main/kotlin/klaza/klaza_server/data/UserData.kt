// Plugin Klaza para Moodle - Server - UserData.kt
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


package klaza.klaza_server.data

import klaza.klaza_server.dtos.UserDTO
import klaza.klaza_server.models.UserModel
import klaza.klaza_server.repositories.*
import klaza.klaza_server.services.ImageService
import org.springframework.beans.factory.annotation.Autowired
import java.security.MessageDigest

class UserData(private var userModel: UserModel) {

    @Autowired lateinit var imageService: ImageService
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var klazaGlobalConfigRepository: KlazaGlobalConfigRepository
    @Autowired lateinit var klazaDiscordAccountModelRepository: KlazaDiscordAccountModelRepository
    @Autowired lateinit var klazaTelegramAccountModelRepository: KlazaTelegramAccountModelRepository
    @Autowired lateinit var klazaWhatsappAccountModelRepository: KlazaWhatsappAccountModelRepository

    private var id: Long = userModel.id!!
    private var username = userModel.firstname + " " + userModel.lastname
    private var email = userModel.email!!
    private var avatar = "https://www.gravatar.com/avatar/" + MessageDigest.getInstance("MD5").update(userModel.email!!.toByteArray(), 0, userModel.email!!.length) + "?s=100&d=mm"
    private var role = ""
    private var courses: List<CourseData>? = null
    private var globalConfig: UserGlobalConfigData? = null
    private var userAccounts: List<UserAccountData>? = null

    fun getId(): Long { return this.id }
    fun getUsername(): String { return this.username }
    fun getEmail(): String { return this.email }
    fun getAvatar(): String { return this.avatar }
    fun getRole(): String { return this.role }
    fun getCourses(): List<CourseData> {

        if (this.courses == null) {
            this.courses = this.courseRepository.findAllByUserId(this.getId()).map { it.toData() }
        }

        return this.courses!!
    }
    fun getGlobalConfig(): UserGlobalConfigData {

        if (this.globalConfig == null) {
            this.globalConfig = this.klazaGlobalConfigRepository.findByUserId(this.getId())!!.toData()
        }

        return this.globalConfig!!

    }
    fun getUserAccounts(): List<UserAccountData> {

        if (this.userAccounts == null) {

            val discord = this.klazaDiscordAccountModelRepository.findByUserId(this.getId())
            val telegram = this.klazaTelegramAccountModelRepository.findByUserId(this.getId())
            val whatsapp = this.klazaWhatsappAccountModelRepository.findByUserId(this.getId())

            this.userAccounts = mutableListOf<UserAccountData>()

            if (discord != null) {
                (this.userAccounts as MutableList<UserAccountData>).add(discord.toData())
            }

            if (telegram != null) {
                (this.userAccounts as MutableList<UserAccountData>).add(telegram.toData())
            }

            if (whatsapp != null) {
                (this.userAccounts as MutableList<UserAccountData>).add(whatsapp.toData())
            }

        }

        return this.userAccounts!!

    }

    fun toDTO(): UserDTO { return UserDTO(this) }

}