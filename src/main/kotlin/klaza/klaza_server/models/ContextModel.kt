// Plugin Klaza para Moodle - Server - Context.kt
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

import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Table

@Entity
@Table(name = "mdl_context")
class ContextModel {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "contextlevel", nullable = false)
    var contextlevel: Long? = null

    @Column(name = "instanceid", nullable = false)
    var instanceid: Long? = null

    // Override

    override fun toString(): String {
        return "Context(id=$id, contextlevel=$contextlevel, instanceid=$instanceid)"
    }

}