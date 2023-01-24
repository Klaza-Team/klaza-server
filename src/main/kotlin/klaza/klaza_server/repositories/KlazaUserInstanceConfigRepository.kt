package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaUserInstanceConfigModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface KlazaUserInstanceConfigRepository : JpaRepository<KlazaUserInstanceConfigModel, Long> {

    @Query(
        "SELECT ui " +
        "FROM KlazaUserInstanceConfigModel ui " +
        "WHERE ui.userInstance.user.id = :userId AND ui.userInstance.course.id = :courseId"
    )
    fun findUserCourseConfigs(userId: Long, courseId: Long): KlazaUserInstanceConfigModel?

}