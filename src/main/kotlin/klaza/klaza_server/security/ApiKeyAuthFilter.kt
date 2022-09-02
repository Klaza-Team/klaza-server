package klaza.klaza_server.security

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

open class ApiKeyAuthFilter: AbstractPreAuthenticatedProcessingFilter() {

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any {
        return request.getHeader("x-api-key") ?: ""
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest): Any? {
        return "N/A"
    }

}