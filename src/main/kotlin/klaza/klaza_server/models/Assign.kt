// Plugin Klaza para Moodle - Server - Assign.kt
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
@Table(name = "mdl_assign")
class Assign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "name", length = 100, nullable = false)
    private var name: String? = null

    @Column(name = "duedate", nullable = false)
    private var dueDate: Long? = null

    @Column(name = "maxattempts", nullable = false)
    private var maxAttempts: Int? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course", nullable = false)
    private var course: Course? = null

    @Column(name = "allowsubmissionsfromdate", nullable = false)
    private var allowsubmissionsfromdate: Long? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDueDate(): Long? {
        return dueDate
    }

    fun setDueDate(dueDate: Long?) {
        this.dueDate = dueDate
    }

    fun getMaxAttempts(): Int? {
        return maxAttempts
    }

    fun setMaxAttempts(maxAttempts: Int?) {
        this.maxAttempts = maxAttempts
    }

    fun getCourse(): Course? {
        return course
    }

    fun setCourse(course: Course?) {
        this.course = course
    }

    fun getAllowsubmissionsfromdate(): Long? {
        return allowsubmissionsfromdate
    }

    fun setAllowsubmissionsfromdate(allowsubmissionsfromdate: Long) {
        this.allowsubmissionsfromdate = allowsubmissionsfromdate
    }

    override fun toString(): String {
        return "Assign(id=$id, name=$name, dueDate=$dueDate, maxAttempts=$maxAttempts, course=$course)"
    }

}