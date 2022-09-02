package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaUserInstance
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaUserInstanceRepository : JpaRepository<KlazaUserInstance, Long> {

    fun findAllByCourse_Id(courseId: Long): List<KlazaUserInstance>

    fun findAllByUser_Id(userId: Long): List<KlazaUserInstance>

    fun findAllByType(type: String): List<KlazaUserInstance>

    fun findOneByUser_IdAndCourse_IdAndType(userId: Long, courseId: Long, type: String): KlazaUserInstance

}