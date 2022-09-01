package klaza.klaza_server.repositories;

import klaza.klaza_server.models.UserInfoData
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoDataRepository: JpaRepository<UserInfoData, Long> {
}