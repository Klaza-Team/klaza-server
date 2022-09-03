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

import javax.persistence.*

@Entity
@Table(name = "mdl_user_info_field")
class UserInfoField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "shortname", length = 100, nullable = false)
    private var shortname: String? = null

    @Column(name = "name", columnDefinition = "LONGTEXT", nullable = false)
    private var name: String? = null

    @Column(name = "datatype", nullable = false)
    private var datatype: String? = null

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    private var description: String? = null

    @Column(name = "required", columnDefinition = "TINYINT", nullable = false)
    private var required: Int? = null

    @Column(name = "defaultdata", columnDefinition = "LONGTEXT", nullable = false)
    private var defaultdata: String? = null

    @Column(name = "param1", columnDefinition = "LONGTEXT", nullable = false)
    private var param1: String? = null

    @Column(name = "param2", columnDefinition = "LONGTEXT", nullable = false)
    private var param2: String? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getShortname(): String? {
        return shortname
    }

    fun setShortname(shortname: String?) {
        this.shortname = shortname
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDatatype(): String? {
        return datatype
    }

    fun setDatatype(datatype: String?) {
        this.datatype = datatype
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getRequired(): Int? {
        return required
    }

    fun setRequired(required: Int?) {
        this.required = required
    }

    fun getDefaultdata(): String? {
        return defaultdata
    }

    fun setDefaultdata(defaultdata: String?) {
        this.defaultdata = defaultdata
    }

    fun getParam1(): String? {
        return param1
    }

    fun setParam1(param1: String?) {
        this.param1 = param1
    }

    fun getParam2(): String? {
        return param2
    }

    fun setParam2(param2: String?) {
        this.param2 = param2
    }

    override fun toString(): String {
        return "UserInfoField(id=$id, shortname=$shortname, name=$name, datatype=$datatype, description=$description, required=$required, defaultdata=$defaultdata, param1=$param1, param2=$param2)"
    }

}