// Plugin Klaza para Moodle - Server - KlazaUserInstanceConfig.kt
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
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "mdl_klaza_user_inst_conf")
class KlazaUserInstanceConfig: KlazaInstanceConfig() {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_instance_id", nullable = false)
    var userInstance: KlazaUserInstance? = null

    @Override
    override fun toString(): String {
        return "KlazaDiscordInstanceConfig(" +
                "id=$id, " +
                "userInstance=$userInstance, " +
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