package klaza.klaza_server.repositories;

import klaza.klaza_server.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {}