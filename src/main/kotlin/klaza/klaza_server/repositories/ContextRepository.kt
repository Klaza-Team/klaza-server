package klaza.klaza_server.repositories;

import klaza.klaza_server.models.Context
import org.springframework.data.jpa.repository.JpaRepository

interface ContextRepository : JpaRepository<Context, Long> {
}