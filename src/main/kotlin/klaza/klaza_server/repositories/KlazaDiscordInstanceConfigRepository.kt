package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaDiscordInstanceConfigModel
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaDiscordInstanceConfigModelRepository: JpaRepository<KlazaDiscordInstanceConfigModel, Long> {
}