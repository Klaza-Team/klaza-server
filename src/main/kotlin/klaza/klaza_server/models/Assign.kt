package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "assign")
open class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}