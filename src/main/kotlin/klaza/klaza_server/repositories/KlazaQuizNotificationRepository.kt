package klaza.klaza_server.repositories;

import klaza.klaza_server.models.KlazaQuizNotification
import org.springframework.data.jpa.repository.JpaRepository

interface KlazaQuizNotificationRepository : JpaRepository<KlazaQuizNotification, Long> {

    fun findAllByQuiz_Id(quizId: Long): List<KlazaQuizNotification>

}