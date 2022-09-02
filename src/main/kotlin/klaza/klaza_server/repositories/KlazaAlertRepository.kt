package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaAlert
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaAlertRepository : JpaRepository<KlazaAlert, Long> {

    fun findAllByCourse_Id(courseId: Long): List<KlazaAlert>

}