package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_config_plugins")
class ConfigPlugins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "plugin", length = 100, nullable = false)
    private var plugin: String? = null

    @Column(name = "name", length = 100, nullable = false)
    private var name: String? = null

    @Column(name = "value", columnDefinition = "LONGTEXT", nullable = false)
    private var value: String? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getPlugin(): String? {
        return plugin
    }

    fun setPlugin(plugin: String?) {
        this.plugin = plugin
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getValue(): String? {
        return value
    }

    fun setValue(value: String?) {
        this.value = value
    }

    override fun toString(): String {
        return "ConfigPlugins(id=$id, plugin=$plugin, name=$name, value=$value)"
    }

}