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

import klaza.klaza_server.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserModel, Long> {

    @Query( "SELECT u\n" +
            "FROM CourseModel c\n" +
            "JOIN ContextModel ct ON c.id = ct.instanceid\n" +
            "JOIN RoleAssignmentsModel ra ON ra.context.id = ct.id\n" +
            "JOIN UserModel u ON u.id = ra.user.id\n" +
            "JOIN RoleModel r ON r.id = ra.role.id\n" +
            "WHERE r.id <= 5 AND c.id = ?1")
    fun findAllByCourseID(courseID: Long): List<UserModel>

    @Query( "SELECT u\n" +
            "FROM CourseModel c\n" +
            "JOIN ContextModel ct ON c.id = ct.instanceid\n" +
            "JOIN RoleAssignmentsModel ra ON ra.context.id = ct.id\n" +
            "JOIN UserModel u ON u.id = ra.user.id\n" +
            "JOIN RoleModel r ON r.id = ra.role.id\n" +
            "WHERE r.id <= 4 AND c.id = ?1")
    fun findAllTeachersByCourseID(courseID: Long): List<UserModel>

    fun findByEmail(email: String): UserModel

    fun findByFirstname(firstname: String): UserModel

    fun findByLastname(lastname: String): UserModel

}