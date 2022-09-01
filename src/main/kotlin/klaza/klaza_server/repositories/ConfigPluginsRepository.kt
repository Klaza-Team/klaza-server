package klaza.klaza_server.repositories;

import klaza.klaza_server.models.ConfigPlugins
import org.springframework.data.jpa.repository.JpaRepository

interface ConfigPluginsRepository: JpaRepository<ConfigPlugins, Long> {
}