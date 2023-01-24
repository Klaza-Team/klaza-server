// Plugin Klaza para Moodle - Server - KlazaTelegramInstanceConfig.kt
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
import klaza.klaza_server.dtos.UserCourseConfigDTO
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "mdl_klaza_tele_inst_conf")
class KlazaTelegramInstanceConfigModel constructor(): KlazaInstanceConfig() {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telegram_instance_id", nullable = false)
    var telegramInstance: KlazaTelegramInstanceModel? = null

    constructor(id: Long?, telegramInstance: KlazaTelegramInstanceModel, config: UserCourseConfigDTO) : this() {
        this.id = id
        this.telegramInstance = telegramInstance
        this.useGlobal = config.use_global
        this.notifyCreateContent = config.notify_create_content
        this.notifyEditContent = config.notify_edit_content
        this.notifyDeleteContent = config.notify_delete_content
        this.notifyDeadline2Days = config.notify_deadline_2_days
        this.notifyDeadline1Day = config.notify_deadline_1_day
        this.notifyDeadline = config.notify_deadline
        this.notifySendAssignment = config.notify_send_assignment
        this.notifyReceiveMessage = config.notify_receive_message
        this.notifyReceiveComment = config.notify_receive_comment
        this.notifyDeleteComment = config.notify_delete_comment
    }

    @Override
    override fun toString(): String {
        return "KlazaDiscordInstanceConfig(" +
                "id=$id, " +
                "telegramInstance=$telegramInstance, " +
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