package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "klaza_alert")
open class KlazaAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}