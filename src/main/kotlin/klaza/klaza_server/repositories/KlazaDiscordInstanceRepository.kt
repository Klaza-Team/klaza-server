package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaDiscordInstance
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaDiscordInstanceRepository : JpaRepository<KlazaDiscordInstance, Long> {

    fun findAllByGuild(guild: String): List<KlazaDiscordInstance>

    fun findAllByChannel(channel: String): List<KlazaDiscordInstance>

    fun findAllByGuildAndChannel(guild: String, channel: String): List<KlazaDiscordInstance>

    fun findAllByCourse_Id(courseId: String): List<KlazaDiscordInstance>

}