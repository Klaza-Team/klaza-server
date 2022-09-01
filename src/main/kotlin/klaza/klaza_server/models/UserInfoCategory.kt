package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_user_info_category")
class UserInfoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "name", length = 100, nullable = false)
    private var name: String? = null

    @Column(name = "sortorder", nullable = false)
    private var sortOrder: Long? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getSortOrder(): Long? {
        return sortOrder
    }

    fun setSortOrder(sortOrder: Long?) {
        this.sortOrder = sortOrder
    }

    override fun toString(): String {
        return "UserInfoCategory(id=$id, name=$name, sortOrder=$sortOrder)"
    }

}