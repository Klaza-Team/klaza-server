// Plugin Klaza para Moodle - Server - KlazaAccount.kt
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

package klaza.klaza_server.classes

import klaza.klaza_server.models.UserModel
import org.hibernate.annotations.Type
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.OneToOne
import javax.persistence.FetchType
import javax.persistence.JoinColumn


@MappedSuperclass
open class KlazaAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserModel? = null

    @Column(name="value", length = 100, nullable = false)
    var value: String? = null

    @Column(name="priority", columnDefinition = "TINYINT(1)", nullable = false)
    @Type(type = "org.hibernate.type.IntegerType")
    var priority: Int? = null

    override fun toString(): String {
        return "KlazaDiscordAccount(id=$id, user=$user, value=$value, priority=$priority)"
    }

}