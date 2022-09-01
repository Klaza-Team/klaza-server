package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_course")
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "fullname", length = 100, nullable = false)
    private var fullname: String? = null

    @Column(name = "shortname", length = 100, nullable = false)
    private var shortname: String? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getFullname(): String? {
        return fullname
    }

    fun setFullname(fullname: String?) {
        this.fullname = fullname
    }

    fun getShortname(): String? {
        return shortname
    }

    fun setShortname(shortname: String?) {
        this.shortname = shortname
    }

    override fun toString(): String {
        return "Course(id=$id, fullname=$fullname, shortname=$shortname)"
    }

}