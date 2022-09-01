package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaUserInstance
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaUserInstanceRepository: JpaRepository<KlazaUserInstance, Long> {
}