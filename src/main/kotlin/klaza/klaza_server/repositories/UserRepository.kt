// Plugin Klaza para Moodle - Server - UserRepository.kt
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

import klaza.klaza_server.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    @Query( "SELECT u\n" +
            "FROM Course c\n" +
            "JOIN Context ct ON c.id = ct.instanceid\n" +
            "JOIN RoleAssignments ra ON ra.context.id = ct.id\n" +
            "JOIN User u ON u.id = ra.user.id\n" +
            "JOIN Role r ON r.id = ra.role.id\n" +
            "WHERE r.id = 5 AND c.id = ?1")
    fun findAllByCourseID(courseID: Long): List<User>

    fun findByEmail(email: String): User

    fun findByFirstname(firstname: String): User

    fun findByLastname(lastname: String): User

}