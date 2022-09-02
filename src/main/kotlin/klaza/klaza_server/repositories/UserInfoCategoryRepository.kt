package klaza.klaza_server.repositories;

import klaza.klaza_server.models.UserInfoCategory
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoCategoryRepository : JpaRepository<UserInfoCategory, Long> {

    fun findByName(name: String): UserInfoCategory

}