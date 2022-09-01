package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "user_info_field")
open class UserInfoField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}