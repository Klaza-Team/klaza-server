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

    override fun toString(): String {
        return "Assign(id=$id, name=$name, dueDate=$dueDate, maxAttempts=$maxAttempts, course=$course)"
    }

}