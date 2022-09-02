package klaza.klaza_server.repositories;

import klaza.klaza_server.models.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CourseRepository : JpaRepository<Course, Long> {

    @Query(
        value = "SELECT c.*\n" +
                "FROM mdl_course c\n" +
                "JOIN mdl_context ct ON c.id = ct.instanceid\n" +
                "JOIN mdl_role_assignments ra ON ra.contextid = ct.id\n" +
                "JOIN mdl_user u ON u.id = ra.userid\n" +
                "JOIN mdl_role r ON r.id = ra.roleid\n" +
                "WHERE u.id = ?1",
        nativeQuery = true)
    fun findAllByUserId(userId: Long): List<Course>

    fun findAllByShortname(shortname: String): List<Course>

    fun findAllByFullname(fullname: String): List<Course>

}