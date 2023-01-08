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

import klaza.klaza_server.data.UserData

class UserDTO(userData: UserData) {

    private var id: Long
    private var username: String
    private var email: String
    private var avatar: String
    private var role: String
    private var courses: List<CourseDTO>
    private var globalConfig: UserConfigDTO
    private var notification_priority: List<UserNotificationAppDTO>

    init {
        this.id = userData.getId()
        this.username = userData.getUsername()
        this.email = userData.getEmail()
        this.avatar = userData.getAvatar()
        this.role = userData.getRole()
        this.courses = userData.getCourses().map { it.toDTO(this.id) }
        this.globalConfig = userData.getGlobalConfig().toDTO()
        this.notification_priority = userData.getUserAccounts().map { it.toDTO() }
    }

    override fun toString(): String {
        return "UserDTO(" +
                "id=$id, " +
                "username='$username', " +
                "email='$email', " +
                "avatar='$avatar', " +
                "role='$role', " +
                "courses=$courses, " +
                "globalConfig=$globalConfig, " +
                "notification_priority=$notification_priority" +
                ")"
    }

}