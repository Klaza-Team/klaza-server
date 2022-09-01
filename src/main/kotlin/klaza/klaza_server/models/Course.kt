package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "course")
open class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}