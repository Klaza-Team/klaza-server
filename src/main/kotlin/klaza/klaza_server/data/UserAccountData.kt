// Plugin Klaza para Moodle - Server - UserAccountData.kt
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

import klaza.klaza_server.classes.KlazaAccount
import klaza.klaza_server.dtos.UserNotificationAppDTO

class UserAccountData(
    private var type: String,
    private var klazaAccount: KlazaAccount) {

    private var id: Long = klazaAccount.id!!
    private var user: UserData? = null
    private var value: String = klazaAccount.value!!
    private var priority: Int = klazaAccount.priority!!

    fun getId(): Long { return this.id }
    fun getType(): String { return this.type }
    fun getUser(): UserData {

        if (this.user == null) {
            this.user = klazaAccount.user!!.toData()
        }

        return this.user!!

    }
    fun getValue(): String { return this.value }
    fun getPriority(): Int { return this.priority }

    fun toDTO(): UserNotificationAppDTO { return UserNotificationAppDTO(this) }

}