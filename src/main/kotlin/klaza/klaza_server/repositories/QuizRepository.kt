package klaza.klaza_server.repositories;

import klaza.klaza_server.models.Quiz
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRepository : JpaRepository<Quiz, Long> {

    fun findByName(name: String): Quiz

    fun findAllByCourse_Id(courseId: Long): List<Quiz>

}