// Plugin Klaza para Moodle - Server - CourseRepository.kt
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

import klaza.klaza_server.models.CourseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CourseRepository : JpaRepository<CourseModel, Long> {

    @Query(
        value = "SELECT c.*\n" +
                "FROM mdl_course c\n" +
                "JOIN mdl_context ct ON c.id = ct.instanceid\n" +
                "JOIN mdl_role_assignments ra ON ra.contextid = ct.id\n" +
                "JOIN mdl_user u ON u.id = ra.userid\n" +
                "JOIN mdl_role r ON r.id = ra.roleid\n" +
                "WHERE u.id = ?1",
        nativeQuery = true)
    fun findAllByUserId(userId: Long): List<CourseModel>

    fun findAllByShortname(shortname: String): List<CourseModel>

    fun findAllByFullname(fullname: String): List<CourseModel>

}