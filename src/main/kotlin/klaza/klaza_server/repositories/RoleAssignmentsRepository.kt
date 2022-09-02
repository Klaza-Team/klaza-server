package klaza.klaza_server.repositories;

import klaza.klaza_server.models.RoleAssignments
import org.springframework.data.jpa.repository.JpaRepository

interface RoleAssignmentsRepository : JpaRepository<RoleAssignments, Long> {

    fun findAllByUser_Id(userId: Long): List<RoleAssignments>

    fun findAllByRole_Id(roleId: Long): List<RoleAssignments>

    fun findAllByContext_Id(contextId: Long): List<RoleAssignments>

}