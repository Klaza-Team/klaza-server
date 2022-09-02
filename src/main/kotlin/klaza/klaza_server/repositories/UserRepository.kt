package klaza.klaza_server.repositories;

import klaza.klaza_server.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    @Query( "SELECT u\n" +
            "FROM Course c\n" +
            "JOIN Context ct ON c.id = ct.instanceid\n" +
            "JOIN RoleAssignments ra ON ra.context.id = ct.id\n" +
            "JOIN User u ON u.id = ra.user.id\n" +
            "JOIN Role r ON r.id = ra.role.id\n" +
            "WHERE r.id = 5 AND c.id = ?1")
    fun findAllByCourseID(courseID: Long): List<User>

    fun findByEmail(email: String): User

    fun findByFirstname(firstname: String): User

    fun findByLastname(lastname: String): User

}