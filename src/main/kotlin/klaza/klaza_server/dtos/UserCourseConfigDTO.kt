// Plugin Klaza para Moodle - Server - CourseConfigDTO.kt
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

import klaza.klaza_server.classes.KlazaInstanceConfig
import klaza.klaza_server.models.KlazaDiscordInstanceConfigModel
import klaza.klaza_server.models.KlazaTelegramInstanceConfigModel

class UserCourseConfigDTO {

    var use_global: Boolean;
    var notify_create_content: Boolean;
    var notify_edit_content: Boolean;
    var notify_delete_content: Boolean;
    var notify_deadline_2_days: Boolean;
    var notify_deadline_1_day: Boolean;
    var notify_deadline: Boolean;
    var notify_send_assignment: Boolean;
    var notify_receive_message: Boolean;
    var notify_receive_comment: Boolean;
    var notify_delete_comment: Boolean;

    constructor() {
        this.use_global = true
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

    constructor(useGlobal: Boolean, notifyCreateContent: Boolean, notifyEditContent: Boolean, notifyDeleteContent: Boolean, notifyDeadline2Days: Boolean, notifyDeadline1Day: Boolean, notifyDeadline: Boolean, notifySendAssignment: Boolean, notifyReceiveMessage: Boolean, notifyReceiveComment: Boolean, notifyDeleteComment: Boolean) {
        this.use_global = useGlobal
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

    constructor(klazaUserInstanceConfig: KlazaInstanceConfig) {
        this.use_global = klazaUserInstanceConfig.useGlobal ?: true
        this.notify_create_content = klazaUserInstanceConfig.notifyCreateContent ?: true
        this.notify_edit_content = klazaUserInstanceConfig.notifyEditContent ?: true
        this.notify_delete_content = klazaUserInstanceConfig.notifyDeleteContent ?: true
        this.notify_deadline_2_days = klazaUserInstanceConfig.notifyDeadline2Days ?: true
        this.notify_deadline_1_day = klazaUserInstanceConfig.notifyDeadline1Day ?: true
        this.notify_deadline = klazaUserInstanceConfig.notifyDeadline ?: true
        this.notify_send_assignment = klazaUserInstanceConfig.notifySendAssignment ?: true
        this.notify_receive_message = klazaUserInstanceConfig.notifyReceiveMessage ?: true
        this.notify_receive_comment = klazaUserInstanceConfig.notifyReceiveComment ?: true
        this.notify_delete_comment = klazaUserInstanceConfig.notifyDeleteComment ?: true
    }

    constructor(klazaDiscordInstanceConfigModel: KlazaDiscordInstanceConfigModel) {
        this.use_global = klazaDiscordInstanceConfigModel.useGlobal ?: true
        this.notify_create_content = klazaDiscordInstanceConfigModel.notifyCreateContent ?: true
        this.notify_edit_content = klazaDiscordInstanceConfigModel.notifyEditContent ?: true
        this.notify_delete_content = klazaDiscordInstanceConfigModel.notifyDeleteContent ?: true
        this.notify_deadline_2_days = klazaDiscordInstanceConfigModel.notifyDeadline2Days ?: true
        this.notify_deadline_1_day = klazaDiscordInstanceConfigModel.notifyDeadline1Day ?: true
        this.notify_deadline = klazaDiscordInstanceConfigModel.notifyDeadline ?: true
        this.notify_send_assignment = klazaDiscordInstanceConfigModel.notifySendAssignment ?: true
        this.notify_receive_message = klazaDiscordInstanceConfigModel.notifyReceiveMessage ?: true
        this.notify_receive_comment = klazaDiscordInstanceConfigModel.notifyReceiveComment ?: true
        this.notify_delete_comment = klazaDiscordInstanceConfigModel.notifyDeleteComment ?: true
    }

    constructor(klazaTelegramInstanceConfigModel: KlazaTelegramInstanceConfigModel) {
        this.use_global = klazaTelegramInstanceConfigModel.useGlobal ?: true
        this.notify_create_content = klazaTelegramInstanceConfigModel.notifyCreateContent ?: true
        this.notify_edit_content = klazaTelegramInstanceConfigModel.notifyEditContent ?: true
        this.notify_delete_content = klazaTelegramInstanceConfigModel.notifyDeleteContent ?: true
        this.notify_deadline_2_days = klazaTelegramInstanceConfigModel.notifyDeadline2Days ?: true
        this.notify_deadline_1_day = klazaTelegramInstanceConfigModel.notifyDeadline1Day ?: true
        this.notify_deadline = klazaTelegramInstanceConfigModel.notifyDeadline ?: true
        this.notify_send_assignment = klazaTelegramInstanceConfigModel.notifySendAssignment ?: true
        this.notify_receive_message = klazaTelegramInstanceConfigModel.notifyReceiveMessage ?: true
        this.notify_receive_comment = klazaTelegramInstanceConfigModel.notifyReceiveComment ?: true
        this.notify_delete_comment = klazaTelegramInstanceConfigModel.notifyDeleteComment ?: true
    }

    override fun toString(): String {
        return "UserCourseConfigDTO(" +
                "use_global=$use_global, " +
                "notify_create_content=$notify_create_content, " +
                "notify_edit_content=$notify_edit_content, " +
                "notify_delete_content=$notify_delete_content, " +
                "notify_deadline_2_days=$notify_deadline_2_days, " +
                "notify_deadline_1_day=$notify_deadline_1_day, " +
                "notify_deadline=$notify_deadline, " +
                "notify_send_assignment=$notify_send_assignment, " +
                "notify_receive_message=$notify_receive_message, " +
                "notify_receive_comment=$notify_receive_comment, " +
                "notify_delete_comment=$notify_delete_comment" +
                ")"
    }

}