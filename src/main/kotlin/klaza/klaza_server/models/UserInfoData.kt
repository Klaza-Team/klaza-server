package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "user_info_data")
open class UserInfoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}