package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_telegram_instance")
class KlazaTelegramInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "guild", length = 100, nullable = false)
    private var guild: String? = null // verificar se realmente é isso

    @Column(name = "channel", length = 100, nullable = false)
    private var channel: String? = null // Verificar se realmente é isso

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private var course: Course? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getGuild(): String? {
        return guild
    }

    fun setGuild(guild: String?) {
        this.guild = guild
    }

    fun getChannel(): String? {
        return channel
    }

    fun setChannel(channel: String?) {
        this.channel = channel
    }

    fun getCourse(): Course? {
        return course
    }

    fun setCourse(course: Course?) {
        this.course = course
    }

    override fun toString(): String {
        return "KlazaTelegramInstance(id=$id, guild=$guild, channel=$channel, course=$course)"
    }

}