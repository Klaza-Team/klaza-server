// Plugin Klaza para Moodle - Server - UserGlobalConfigDTO.kt
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

import klaza.klaza_server.models.KlazaGlobalConfigModel

class UserGlobalConfigDTO {

    var notify_create_content: Boolean
    var notify_edit_content: Boolean
    var notify_delete_content: Boolean
    var notify_deadline_2_days: Boolean
    var notify_deadline_1_day: Boolean
    var notify_deadline: Boolean
    var notify_send_assignment: Boolean
    var notify_receive_message: Boolean
    var notify_receive_comment: Boolean
    var notify_delete_comment: Boolean

    constructor() {
        this.notify_create_content = true
        this.notify_edit_content = true
        this.notify_delete_content = true
        this.notify_deadline_2_days = true
        this.notify_deadline_1_day = true
        this.notify_deadline = true
        this.notify_send_assignment = true
        this.notify_receive_message = true
        this.notify_receive_comment = true
        this.notify_delete_comment = true
    }

    constructor(notifyCreateContent: Boolean, notifyEditContent: Boolean, notifyDeleteContent: Boolean, notifyDeadline2Days: Boolean, notifyDeadline1Day: Boolean, notifyDeadline: Boolean, notifySendAssignment: Boolean, notifyReceiveMessage: Boolean, notifyReceiveComment: Boolean, notifyDeleteComment: Boolean) {
        this.notify_create_content = notifyCreateContent
        this.notify_edit_content = notifyEditContent
        this.notify_delete_content = notifyDeleteContent
        this.notify_deadline_2_days = notifyDeadline2Days
        this.notify_deadline_1_day = notifyDeadline1Day
        this.notify_deadline = notifyDeadline
        this.notify_send_assignment = notifySendAssignment
        this.notify_receive_message = notifyReceiveMessage
        this.notify_receive_comment = notifyReceiveComment
        this.notify_delete_comment = notifyDeleteComment
    }

    constructor(klazaGlobalConfigModel: KlazaGlobalConfigModel) {
        this.notify_create_content = klazaGlobalConfigModel.notifyCreateContent!!
        this.notify_edit_content = klazaGlobalConfigModel.notifyEditContent!!
        this.notify_delete_content = klazaGlobalConfigModel.notifyDeleteContent!!
        this.notify_deadline_2_days = klazaGlobalConfigModel.notifyDeadline2Days!!
        this.notify_deadline_1_day = klazaGlobalConfigModel.notifyDeadline1Day!!
        this.notify_deadline = klazaGlobalConfigModel.notifyDeadline!!
        this.notify_send_assignment = klazaGlobalConfigModel.notifySendAssignment!!
        this.notify_receive_message = klazaGlobalConfigModel.notifyReceiveMessage!!
        this.notify_receive_comment = klazaGlobalConfigModel.notifyReceiveComment!!
        this.notify_delete_comment = klazaGlobalConfigModel.notifyDeleteComment!!
    }

}