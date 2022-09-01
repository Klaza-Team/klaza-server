package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_alert")
class KlazaAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "text", columnDefinition = "LONGTEXT", nullable = false)
    private var text: String? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private var course: Course? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String?) {
        this.text = text
    }

    fun getCourse(): Course? {
        return course
    }

    fun setCourse(course: Course?) {
        this.course = course
    }

    override fun toString(): String {
        return "KlazaAlert(id=$id, text=$text, course=$course)"
    }

}