// Plugin Klaza para Moodle - Server - KlazaUserInstanceRepository.kt
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

import klaza.klaza_server.models.KlazaUserInstance
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaUserInstanceRepository : JpaRepository<KlazaUserInstance, Long> {

    fun findAllByCourse_Id(courseId: Long): List<KlazaUserInstance>

    fun findAllByUser_Id(userId: Long): List<KlazaUserInstance>

    fun findAllByType(type: String): List<KlazaUserInstance>

    fun findOneByUser_IdAndCourse_IdAndType(userId: Long, courseId: Long, type: String): KlazaUserInstance?

    fun findAllByCourse_IdAndType(courseId: Long, type: String): List<KlazaUserInstance>

}