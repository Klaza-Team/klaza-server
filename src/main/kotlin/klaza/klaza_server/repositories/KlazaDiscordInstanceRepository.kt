package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaDiscordInstance
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaDiscordInstanceRepository: JpaRepository<KlazaDiscordInstance, Long> {
}