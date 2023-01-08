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

import klaza.klaza_server.data.CourseUserConfigData

class CourseConfigDTO(courseUserConfigData: CourseUserConfigData) {

    var use_global: Boolean
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

    init {
        this.use_global = courseUserConfigData.getUseGlobal()
        this.notify_create_content = courseUserConfigData.getNotifyCreateContent()
        this.notify_edit_content = courseUserConfigData.getNotifyEditContent()
        this.notify_delete_content = courseUserConfigData.getNotifyDeleteContent()
        this.notify_deadline_2_days = courseUserConfigData.getNotifyDeadline2Days()
        this.notify_deadline_1_day = courseUserConfigData.getNotifyDeadline1Day()
        this.notify_deadline = courseUserConfigData.getNotifyDeadline()
        this.notify_send_assignment = courseUserConfigData.getNotifySendAssignment()
        this.notify_receive_message = courseUserConfigData.getNotifyReceiveMessage()
        this.notify_receive_comment = courseUserConfigData.getNotifyReceiveComment()
        this.notify_delete_comment = courseUserConfigData.getNotifyDeleteComment()
    }

    override fun toString(): String {
        return "CourseConfigDTO(" +
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