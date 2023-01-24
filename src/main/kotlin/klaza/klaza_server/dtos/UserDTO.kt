// Plugin Klaza para Moodle - Server - UserDTO.kt
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

package klaza.klaza_server.dtos

import klaza.klaza_server.models.CourseModel
import klaza.klaza_server.models.UserModel
import java.util.Base64

class UserDTO {

    var id: Long
    var username: String
    var email: String
    var avatar: String
    var role: String
    var notification_priority: List<UserNotificationAppDTO>
    var global_config: UserGlobalConfigDTO

    constructor(id: Long, username: String, email: String, avatar: String, role: String, notification_priority: List<UserNotificationAppDTO>, global_config: UserGlobalConfigDTO) {
        this.id = id
        this.username = username
        this.email = email
        this.avatar = avatar
        this.role = role
        this.notification_priority = notification_priority
        this.global_config = global_config
    }

    constructor(user: UserModel, notification_priority: List<UserNotificationAppDTO>, global_config: UserGlobalConfigDTO) {
        this.id = user.id!!
        this.username = user.firstname + " " + user.lastname
        this.email = user.email!!
        this.avatar = this.getRandomAvatar()
        this.role = ""
        this.notification_priority = notification_priority
        this.global_config = global_config
    }

    private fun getRandomAvatar(): String {
        return "https://robohash.org/${this.email}"
    }

    override fun toString(): String {
        return "UserDTO(id=$id, username='$username', email='$email', avatar='$avatar', role='$role', notification_priority=$notification_priority, global_config=$global_config)"
    }

}