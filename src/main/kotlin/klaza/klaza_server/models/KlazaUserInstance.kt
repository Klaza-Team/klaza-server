package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_user_instance")
class KlazaUserInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private var course: Course? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "klaza_user_id", nullable = false)
    private var user: User? = null

    @Column(name = "type", columnDefinition = "LONGTEXT",nullable = false)
    private var type: String? = null // DISCORD, TELEGRAM, WHATSAPP

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getCourse(): Course? {
        return course
    }

    fun setCourse(course: Course?) {
        this.course = course
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    override fun toString(): String {
        return "KlazaUserInstance(id=$id, course=$course, user=$user, type=$type)"
    }

}