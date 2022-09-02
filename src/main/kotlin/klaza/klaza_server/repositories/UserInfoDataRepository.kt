package klaza.klaza_server.repositories;

import klaza.klaza_server.models.UserInfoData
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoDataRepository : JpaRepository<UserInfoData, Long> {

    fun findAllByUser_Id(userId: Long): List<UserInfoData>

    fun findAllByField_Id(fieldId: Long): List<UserInfoData>

    fun findOneByUser_IdAndField_Id(userId: Long, fieldId: Long): UserInfoData

}