// Plugin Klaza para Moodle - Server - UserInfoField.kt
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
@Table(name = "mdl_user_info_field")
class UserInfoFieldModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "shortname", length = 100, nullable = false)
    var shortname: String? = null

    @Column(name = "name", columnDefinition = "LONGTEXT", nullable = false)
    var name: String? = null

    @Column(name = "datatype", nullable = false)
    var datatype: String? = null

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    var description: String? = null

    @Column(name = "required", columnDefinition = "TINYINT", nullable = false)
    var required: Int? = null

    @Column(name = "defaultdata", columnDefinition = "LONGTEXT", nullable = false)
    var defaultdata: String? = null

    @Column(name = "param1", columnDefinition = "LONGTEXT", nullable = false)
    var param1: String? = null

    @Column(name = "param2", columnDefinition = "LONGTEXT", nullable = false)
    var param2: String? = null

    override fun toString(): String {
        return "UserInfoField(id=$id, shortname=$shortname, name=$name, datatype=$datatype, description=$description, required=$required, defaultdata=$defaultdata, param1=$param1, param2=$param2)"
    }

}