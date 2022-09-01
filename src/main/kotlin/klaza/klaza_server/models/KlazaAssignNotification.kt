package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "klaza_assign_notification")
open class KlazaAssignNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}