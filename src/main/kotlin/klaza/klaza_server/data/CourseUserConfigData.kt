// Plugin Klaza para Moodle - Server - CouseConfigData.kt
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

import klaza.klaza_server.dtos.CourseConfigDTO
import klaza.klaza_server.dtos.CourseDTO
import klaza.klaza_server.dtos.CourseUserConfigDTO
import klaza.klaza_server.models.KlazaUserInstanceConfigModel

class CourseUserConfigData(klazaUserInstanceConfig: KlazaUserInstanceConfigModel) {

    private var klazaUserInstanceConfigModel: KlazaUserInstanceConfigModel = klazaUserInstanceConfig

    private var id: Long = klazaUserInstanceConfig.id!!
    private var user: UserData? = null
    private var useGlobal: Boolean = klazaUserInstanceConfig.useGlobal ?: true
    private var notifyCreateContent: Boolean  = klazaUserInstanceConfig.notifyCreateContent ?: true
    private var notifyEditContent: Boolean = klazaUserInstanceConfig.notifyEditContent ?: true
    private var notifyDeleteContent: Boolean = klazaUserInstanceConfig.notifyDeleteContent ?: true
    private var notifyDeadline2Days: Boolean = klazaUserInstanceConfig.notifyDeadline2Days ?: true
    private var notifyDeadline1Day: Boolean = klazaUserInstanceConfig.notifyDeadline1Day ?: true
    private var notifyDeadline: Boolean = klazaUserInstanceConfig.notifyDeadline ?: true
    private var notifySendAssignment: Boolean = klazaUserInstanceConfig.notifySendAssignment ?: true
    private var notifyReceiveMessage: Boolean = klazaUserInstanceConfig.notifyReceiveMessage ?: true
    private var notifyReceiveComment: Boolean = klazaUserInstanceConfig.notifyReceiveComment ?: true
    private var notifyDeleteComment: Boolean = klazaUserInstanceConfig.notifyDeleteComment ?: true

    fun getID(): Long { return this.id }
    fun getUser(): UserData {

        if (this.user == null) {
            this.user = this.klazaUserInstanceConfigModel.userInstance!!.user!!.toData()
        }

        return this.user!!

    }
    fun getUseGlobal(): Boolean { return this.useGlobal }
    fun getNotifyCreateContent(): Boolean { return this.notifyCreateContent }
    fun getNotifyEditContent(): Boolean { return this.notifyEditContent }
    fun getNotifyDeleteContent(): Boolean { return this.notifyDeleteContent }
    fun getNotifyDeadline2Days(): Boolean { return this.notifyDeadline2Days }
    fun getNotifyDeadline1Day(): Boolean { return this.notifyDeadline1Day }
    fun getNotifyDeadline(): Boolean { return this.notifyDeadline }
    fun getNotifySendAssignment(): Boolean { return this.notifySendAssignment }
    fun getNotifyReceiveMessage(): Boolean { return this.notifyReceiveMessage }
    fun getNotifyReceiveComment(): Boolean { return this.notifyReceiveComment }
    fun getNotifyDeleteComment(): Boolean { return this.notifyDeleteComment }

    fun toDTO(): CourseUserConfigDTO { return CourseUserConfigDTO(this) }

}