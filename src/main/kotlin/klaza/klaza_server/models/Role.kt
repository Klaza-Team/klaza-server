package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_role")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "shortname", length = 100, nullable = false)
    var shortname: String? = null

    @Column(name = "archetype", length = 30, nullable = false)
    var archetype: String? = null

}