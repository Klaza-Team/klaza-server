package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_discord_instance")
class KlazaDiscordInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "guild", length = 100, nullable = false)
    private var guild: String? = null

    @Column(name = "channel", length = 100, nullable = false)
    private var channel: String? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
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
        return "KlazaDiscordInstance(id=$id, guild=$guild, channel=$channel, course=$course)"
    }

}