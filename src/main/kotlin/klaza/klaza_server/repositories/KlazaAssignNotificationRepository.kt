package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaAssignNotification
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaAssignNotificationRepository: JpaRepository<KlazaAssignNotification, Long> {
}