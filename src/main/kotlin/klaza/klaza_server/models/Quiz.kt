package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "quiz")
open class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}