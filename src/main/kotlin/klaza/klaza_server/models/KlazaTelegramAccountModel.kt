// Plugin Klaza para Moodle - Server - KlazaTelegramAccount.kt
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

package klaza.klaza_server.models

import klaza.klaza_server.classes.KlazaAccount
import klaza.klaza_server.dtos.UserNotificationAppDTO
import org.springframework.context.ApplicationContext
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "mdl_klaza_tele_accounts")
class KlazaTelegramAccountModel constructor(): KlazaAccount() {

    constructor(id: Long?, user: UserModel, value: String, priority: Int) : this() {
        this.id = id
        this.user = user
        this.value = value
        this.priority = priority
    }

    fun toDTO(): UserNotificationAppDTO { return UserNotificationAppDTO(this) }

}