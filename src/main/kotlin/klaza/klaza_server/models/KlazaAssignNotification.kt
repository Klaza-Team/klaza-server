package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_assign_notification")
class KlazaAssignNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assign_id", nullable = false)
    private var assign: Assign? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getAssign(): Assign? {
        return assign
    }

    fun setAssign(assign: Assign?) {
        this.assign = assign
    }

    override fun toString(): String {
        return "KlazaAssignNotification(id=$id, assign=$assign)"
    }

}