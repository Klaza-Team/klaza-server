// Plugin Klaza para Moodle - Server - UserGlobalConfigData.kt
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
import klaza.klaza_server.dtos.UserConfigDTO
import klaza.klaza_server.models.CourseModel
import klaza.klaza_server.models.KlazaGlobalConfigModel
import klaza.klaza_server.models.UserModel
import klaza.klaza_server.services.ImageService
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

class UserGlobalConfigData(private var klazaGlobalConfigModel: KlazaGlobalConfigModel) {

    private var id: Long = klazaGlobalConfigModel.id!!
    private var user: UserData? = null
    private var notifyCreateContent: Boolean = klazaGlobalConfigModel.notifyCreateContent!!
    private var notifyEditContent: Boolean = klazaGlobalConfigModel.notifyEditContent!!
    private var notifyDeleteContent: Boolean = klazaGlobalConfigModel.notifyDeleteContent!!
    private var notifyDeadline2Days: Boolean = klazaGlobalConfigModel.notifyDeadline2Days!!
    private var notifyDeadline1Day: Boolean = klazaGlobalConfigModel.notifyDeadline1Day!!
    private var notifyDeadline: Boolean = klazaGlobalConfigModel.notifyDeadline!!
    private var notifySendAssignment: Boolean = klazaGlobalConfigModel.notifySendAssignment!!
    private var notifyReceiveMessage: Boolean = klazaGlobalConfigModel.notifyReceiveMessage!!
    private var notifyReceiveComment: Boolean = klazaGlobalConfigModel.notifyReceiveComment!!
    private var notifyDeleteComment: Boolean = klazaGlobalConfigModel.notifyDeleteComment!!

    fun getId(): Long { return this.id }
    fun getUser(): UserData {

        if (this.user == null) {
            this.user = this.klazaGlobalConfigModel.user!!.toData()
        }

        return this.user!!
    }
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

    fun toDTO(): UserConfigDTO { return UserConfigDTO(this) }

}