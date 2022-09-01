package klaza.klaza_server.repositories;

import klaza.klaza_server.models.Assign
import org.springframework.data.jpa.repository.JpaRepository

interface AssignRepository : JpaRepository<Assign, Long> {
}