package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "klaza_telegram_instance")
open class KlazaTelegramInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}