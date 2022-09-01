package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "config_plugins")
open class ConfigPlugins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}