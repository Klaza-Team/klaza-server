package klaza.klaza_server.security

import klaza.klaza_server.libs.Colors
import klaza.klaza_server.repositories.ConfigPluginsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component

open class ApiKeyAuthManager(configPluginsRepository: ConfigPluginsRepository): AuthenticationManager {

    private val configPluginsRepository = configPluginsRepository

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {

        val apiKey: String = authentication.principal.toString()

        if (apiKey == this.configPluginsRepository.findKlazaConfigAuth().getValue()) {
            authentication.isAuthenticated = true
        }
        else {
            throw BadCredentialsException("API key not valid")
        }

        return authentication

    }

}