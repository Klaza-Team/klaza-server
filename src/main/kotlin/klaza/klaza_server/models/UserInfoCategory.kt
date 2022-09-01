package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "user_info_category")
open class UserInfoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}