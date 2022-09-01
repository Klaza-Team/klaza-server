package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaTelegramInstance
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaTelegramInstanceRepository: JpaRepository<KlazaTelegramInstance, Long> {
}