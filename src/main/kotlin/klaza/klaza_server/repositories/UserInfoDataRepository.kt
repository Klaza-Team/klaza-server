// Plugin Klaza para Moodle - Server - UserInfoDataRepository.kt
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

package klaza.klaza_server.repositories;

import klaza.klaza_server.models.UserInfoData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserInfoDataRepository : JpaRepository<UserInfoData, Long> {

    @Query( "SELECT d \n" +
            "FROM UserInfoData d\n" +
            "JOIN UserInfoField f ON f.id = d.field.id\n" +
            "WHERE d.user.id = ?1 AND f.shortname LIKE 'klaza_%'")
    fun findAllKlazaUserInfoDataByUserId(userId: Long): List<UserInfoData>

}