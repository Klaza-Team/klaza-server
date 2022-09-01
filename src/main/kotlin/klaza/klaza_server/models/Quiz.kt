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