// Plugin Klaza para Moodle - Server - ApiKeyAuthManager.kt
// Copyright (C) 2022 Klaza Team

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

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