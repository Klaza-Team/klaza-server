package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaTelegramInstanceConfigModel
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaTelegramInstanceConfigRepository: JpaRepository<KlazaTelegramInstanceConfigModel, Long> {
}