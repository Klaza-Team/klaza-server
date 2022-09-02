package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_role_assignments")
class RoleAssignments {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid", nullable = false)
    private var role: Role? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contextid", nullable = false)
    private var context: Context? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", nullable = false)
    private var user: User? = null

    // Getters and Setters

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getRole(): Role? {
        return role
    }

    fun setRole(role: Role?) {
        this.role = role
    }

    fun getContext(): Context? {
        return context
    }

    fun setContext(context: Context?) {
        this.context = context
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    // Overrides

    override fun toString(): String {
        return "RoleAssigments(id=$id, role=$role, context=$context)"
    }

}