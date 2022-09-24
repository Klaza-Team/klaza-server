// Plugin Klaza para Moodle - Server - RoleAssignments.kt
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
@Table(name = "mdl_role_assignments")
class RoleAssignments {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid", nullable = false)
    var role: Role? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contextid", nullable = false)
    var context: Context? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", nullable = false)
    var user: User? = null

    // Overrides

    override fun toString(): String {
        return "RoleAssigments(id=$id, role=$role, context=$context)"
    }

}