package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "klaza_discord_instance")
open class KlazaDiscordInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}