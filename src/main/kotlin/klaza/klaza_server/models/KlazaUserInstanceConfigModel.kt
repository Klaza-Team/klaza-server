// Plugin Klaza para Moodle - Server - KlazaUserInstanceConfigModel.kt
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
@Table(name = "mdl_klaza_user_inst_conf")
class KlazaUserInstanceConfigModel constructor(): KlazaInstanceConfig() {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_instance_id", nullable = false)
    var userInstance: KlazaUserInstanceModel? = null

    constructor(id: Long?, userInstance: KlazaUserInstanceModel, useGlobal: Boolean?, notifyCreateContent: Boolean?, notifyEditContent: Boolean?, notifyDeleteContent: Boolean?, notifyDeadline2Days: Boolean?, notifyDeadline1Day: Boolean?, notifyDeadline: Boolean?, notifySendAssignment: Boolean?, notifyReceiveMessage: Boolean?, notifyReceiveComment: Boolean?, notifyDeleteComment: Boolean?): this() {
        this.id = id
        this.userInstance = userInstance
        this.useGlobal = useGlobal
        this.notifyCreateContent = notifyCreateContent
        this.notifyEditContent = notifyEditContent
        this.notifyDeleteContent = notifyDeleteContent
        this.notifyDeadline2Days = notifyDeadline2Days
        this.notifyDeadline1Day = notifyDeadline1Day
        this.notifyDeadline = notifyDeadline
        this.notifySendAssignment = notifySendAssignment
        this.notifyReceiveMessage = notifyReceiveMessage
        this.notifyReceiveComment = notifyReceiveComment
        this.notifyDeleteComment = notifyDeleteComment
    }

    constructor(id: Long?, userInstance: KlazaUserInstanceModel) : this() {
        this.id = id
        this.userInstance = userInstance
        this.useGlobal = true
        this.notifyCreateContent = true
        this.notifyEditContent = true
        this.notifyDeleteContent = true
        this.notifyDeadline2Days = true
        this.notifyDeadline1Day = true
        this.notifyDeadline = true
        this.notifySendAssignment = true
        this.notifyReceiveMessage = true
        this.notifyReceiveComment = true
        this.notifyDeleteComment = true
    }

    fun toDTO(): UserCourseConfigDTO { return UserCourseConfigDTO(this) }

    @Override
    override fun toString(): String {
        return "KlazaUserInstanceConfigModel(" +
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