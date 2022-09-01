package klaza.klaza_server.repositories;

import klaza.klaza_server.models.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long> {
}