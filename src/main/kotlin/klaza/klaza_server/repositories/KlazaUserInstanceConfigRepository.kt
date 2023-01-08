package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaUserInstanceConfigModel
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaUserInstanceConfigRepository : JpaRepository<KlazaUserInstanceConfigModel, Long> {

    fun findAllByUserInstanceId_CourseId(courseId: Long): List<KlazaUserInstanceConfigModel>

}