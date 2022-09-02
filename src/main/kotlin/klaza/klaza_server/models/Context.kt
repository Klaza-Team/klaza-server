package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_context")
class Context {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "contextlevel", nullable = false)
    private var contextlevel: Long? = null

    @Column(name = "instanceid", nullable = false)
    private var instanceid: Long? = null

    // Getters and Setters

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getContextlevel(): Long? {
        return contextlevel
    }

    fun setContextlevel(contextlevel: Long?) {
        this.contextlevel = contextlevel
    }

    fun getInstanceid(): Long? {
        return instanceid
    }

    fun setInstanceid(instanceid: Long?) {
        this.instanceid = instanceid
    }

    // Override

    override fun toString(): String {
        return "Context(id=$id, contextlevel=$contextlevel, instanceid=$instanceid)"
    }

}