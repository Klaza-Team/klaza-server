// Plugin Klaza para Moodle - Server - UserNotificationAppDTO.kt
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

import klaza.klaza_server.data.UserAccountData

class UserNotificationAppDTO(userAccountData: UserAccountData) {

    private var id: Long
    private var type: String
    private var value: String
    private var priority: Int

    init {
        this.id = userAccountData.getId()
        this.type = userAccountData.getType()
        this.value = userAccountData.getValue()
        this.priority = userAccountData.getPriority()
    }

    override fun toString(): String {
        return "UserNotificationAppDTO(" +
                "id=$id, " +
                "type='$type', " +
                "priority=$priority, " +
                "value='$value'" +
                ")"
    }

}