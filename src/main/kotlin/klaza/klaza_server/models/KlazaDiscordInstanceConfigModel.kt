// Plugin Klaza para Moodle - Server - KlazaDiscordInstanceConfig.kt
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

import klaza.klaza_server.classes.KlazaInstanceConfig
import klaza.klaza_server.data.DiscordInstanceConfData
import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_disc_inst_conf")
class KlazaDiscordInstanceConfigModel: KlazaInstanceConfig() {

    @OneToOne(fetch = FetchType.EAGER)
    var discordInstance: KlazaDiscordInstanceModel? = null

    fun toData(): DiscordInstanceConfData { return DiscordInstanceConfData(this) }

    @Override
    override fun toString(): String {
        return "KlazaDiscordInstanceConfig(" +
                "id=$id, " +
                "discordInstance=$discordInstance, " +
                "useGlobal=$useGlobal, " +
                "notifyCreateContent=$notifyCreateContent, " +
                "notifyEditContent=$notifyEditContent, " +
                "notifyDeleteContent=$notifyDeleteContent, " +
                "notifyDeadline2Days=$notifyDeadline2Days, " +
                "notifyDeadline1Day=$notifyDeadline1Day, " +
                "notifyDeadline=$notifyDeadline, " +
                "notifySendAssignment=$notifySendAssignment, " +
                "notifyReceiveMessage=$notifyReceiveMessage, " +
                "notifyReceiveComment=$notifyReceiveComment" +
                "notifyDeleteComment=$notifyDeleteComment" +
                ")"
    }

}