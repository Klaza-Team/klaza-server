package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaAssignNotification
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaAssignNotificationRepository : JpaRepository<KlazaAssignNotification, Long> {

    fun findAllByAssign_Id(assignId: Long): List<KlazaAssignNotification>

}