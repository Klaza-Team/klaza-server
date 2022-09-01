package klaza.klaza_server.repositories;

import klaza.klaza_server.models.Quiz
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRepository : JpaRepository<Quiz, Long> {
}