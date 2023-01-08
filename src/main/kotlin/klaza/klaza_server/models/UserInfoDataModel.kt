// Plugin Klaza para Moodle - Server - UserInfoData.kt
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
import javax.persistence.JoinColumn
import javax.persistence.FetchType
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "mdl_user_info_data")
class UserInfoDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    var user: UserModel? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldid", referencedColumnName = "id")
    var field: UserInfoFieldModel? = null

    @Column(name = "data", columnDefinition = "LONGTEXT", nullable = false)
    var data: String? = null

    @Column(name = "dataformat", columnDefinition = "TINYINT", nullable = false)
    var dataFormat: Int? = null

    override fun toString(): String {
        return "UserInfoData(id=$id, user=$user, field=$field, data=$data, dataFormat=$dataFormat)"
    }

}