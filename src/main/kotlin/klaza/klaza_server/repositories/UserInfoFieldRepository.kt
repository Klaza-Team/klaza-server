package klaza.klaza_server.repositories;

import klaza.klaza_server.models.UserInfoField
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoFieldRepository: JpaRepository<UserInfoField, Long> {
}