package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "klaza_user_instance")
open class KlazaUserInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}