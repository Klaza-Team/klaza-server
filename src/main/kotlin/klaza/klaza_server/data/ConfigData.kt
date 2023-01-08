// Plugin Klaza para Moodle - Server - ConfigData.kt
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

import klaza.klaza_server.classes.KlazaInstanceConfig
import klaza.klaza_server.dtos.CourseConfigDTO
import klaza.klaza_server.dtos.CourseUserConfigDTO
import javax.persistence.Column

class ConfigData(private var klazaInstanceConfig: KlazaInstanceConfig) {

    private var useGlobal: Boolean = klazaInstanceConfig.useGlobal!!
    private var notifyCreateContent: Boolean = klazaInstanceConfig.notifyCreateContent!!
    private var notifyEditContent: Boolean = klazaInstanceConfig.notifyEditContent!!
    private var notifyDeleteContent: Boolean = klazaInstanceConfig.notifyDeleteContent!!
    private var notifyDeadline2Days: Boolean = klazaInstanceConfig.notifyDeadline2Days!!
    private var notifyDeadline1Day: Boolean = klazaInstanceConfig.notifyDeadline1Day!!
    private var notifyDeadline: Boolean = klazaInstanceConfig.notifyDeadline!!
    private var notifySendAssignment: Boolean = klazaInstanceConfig.notifySendAssignment!!
    private var notifyReceiveMessage: Boolean = klazaInstanceConfig.notifyReceiveMessage!!
    private var notifyReceiveComment: Boolean = klazaInstanceConfig.notifyReceiveComment!!
    private var notifyDeleteComment: Boolean = klazaInstanceConfig.notifyDeleteComment!!

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

    override fun toString(): String {
        return "ConfigData(" +
                "useGlobal=$useGlobal, " +
                "notifyCreateContent=$notifyCreateContent, " +
                "notifyEditContent=$notifyEditContent, " +
                "notifyDeleteContent=$notifyDeleteContent, " +
                "notifyDeadline2Days=$notifyDeadline2Days, " +
                "notifyDeadline1Day=$notifyDeadline1Day, " +
                "notifyDeadline=$notifyDeadline, " +
                "notifySendAssignment=$notifySendAssignment, " +
                "notifyReceiveMessage=$notifyReceiveMessage, " +
                "notifyReceiveComment=$notifyReceiveComment, " +
                "notifyDeleteComment=$notifyDeleteComment" +
                ")"
    }


}