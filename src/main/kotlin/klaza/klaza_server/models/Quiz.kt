// Plugin Klaza para Moodle - Server - Quiz.kt
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
@Table(name = "mdl_quiz")
class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "name", length = 100, nullable = false)
    private var name: String? = null

    @Column(name="timelimit", nullable = false)
    private var timeLimit: Long? = null

    @Column(name="attempts", nullable = false)
    private var attempts: Int? = null

    @Column(name="timecreated", nullable = false)
    private var timeCreated: Long? = null

    @Column(name="timeopen", nullable = false)
    private var timeOpen: Long? = null

    @Column(name="timeclose", nullable = false)
    private var timeClose: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course", nullable = false)
    private var course: Course? = null

    fun getID(): Long? {
        return id
    }

    fun setID(id: Long?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getTimeLimit(): Long? {
        return timeLimit
    }

    fun setTimeLimit(timeLimit: Long?) {
        this.timeLimit = timeLimit
    }

    fun getAttempts(): Int? {
        return attempts
    }

    fun setAttempts(attempts: Int?) {
        this.attempts = attempts
    }

    fun getTimeCreated(): Long? {
        return timeCreated
    }

    fun setTimeCreated(timeCreated: Long?) {
        this.timeCreated = timeCreated
    }

    fun getTimeOpen(): Long? {
        return timeOpen
    }

    fun setTimeOpen(timeOpen: Long) {
        this.timeOpen = timeOpen
    }

    fun getTimeClose(): Long? {
        return timeClose
    }

    fun setTimeClose(timeClose: Long) {
        this.timeClose = timeClose
    }

    fun getCourse(): Course? {
        return course
    }

    fun setCourse(course: Course?) {
        this.course = course
    }

    override fun toString(): String {
        return "Quiz(id=$id, name=$name, timeLimit=$timeLimit, attempts=$attempts, timeCreated=$timeCreated, course=$course)"
    }

}