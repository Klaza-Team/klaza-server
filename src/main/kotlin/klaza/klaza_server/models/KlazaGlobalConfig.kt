// Plugin Klaza para Moodle - Server - KlazaGlobalConfig.kt
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
@Table(name = "mdl_klaza_global_config")
class KlazaGlobalConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @Column(name = "notify_create_content", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyCreateContent: Boolean? = null

    @Column(name = "notify_edit_content", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyEditContent: Boolean? = null

    @Column(name = "notify_delete_content", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyDeleteContent: Boolean? = null

    @Column(name = "notify_deadline_2_days", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyDeadline2Days: Boolean? = null

    @Column(name = "notify_deadline_1_day", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyDeadline1Day: Boolean? = null

    @Column(name = "notify_deadline", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyDeadline: Boolean? = null

    @Column(name = "notify_send_assignment", columnDefinition = "TINYINT(1)", nullable = false)
    var notifySendAssignment: Boolean? = null

    @Column(name = "notify_receive_message", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyReceiveMessage: Boolean? = null

    @Column(name = "notify_receive_comment", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyReceiveComment: Boolean? = null

    @Column(name = "notify_delete_comment", columnDefinition = "TINYINT(1)", nullable = false)
    var notifyDeleteComment: Boolean? = null

    @Override
    override fun toString(): String {
        return "KlazaDiscordInstanceConfig(" +
                "id=$id, " +
                "user=$user, " +
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