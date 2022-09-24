// Plugin Klaza para Moodle - Server - KlazaAssignNotification.kt
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

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_assign_notification")
class KlazaAssignNotification() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "eventname", nullable = false)
    var eventname: String? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assign_id", nullable = false)
    var assign: Assign? = null

    constructor(assign: Assign, eventName: String) : this() {
        this.assign = assign
        this.eventname = eventName
    }

    constructor(id: Long, assign: Assign, eventName: String) : this() {
        this.id = id
        this.assign = assign
        this.eventname = eventName
    }

    override fun toString(): String {
        return "KlazaAssignNotification(id=$id, assign=$assign)"
    }

}