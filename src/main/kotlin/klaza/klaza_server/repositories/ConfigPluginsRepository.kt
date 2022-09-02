package klaza.klaza_server.repositories;

import klaza.klaza_server.models.ConfigPlugins
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConfigPluginsRepository : JpaRepository<ConfigPlugins, Long> {

    fun findAllByPlugin(plugin: String): List<ConfigPlugins>

    fun findAllByName(name: String): List<ConfigPlugins>

    fun findByPluginAndName(plugin: String, name: String): List<ConfigPlugins>

    @Query( "SELECT c " +
            "FROM ConfigPlugins c " +
            "WHERE c.plugin = 'local_klaza' AND c.name = ?1")
    fun findKlazaConfigByName(name: String): ConfigPlugins

    @Query( "SELECT c " +
            "FROM ConfigPlugins c " +
            "WHERE c.plugin = 'local_klaza'")
    fun findAllKlazaConfig(): List<ConfigPlugins>

    @Query( "SELECT c " +
            "FROM ConfigPlugins c " +
            "WHERE c.plugin = 'local_klaza' AND c.name = 'server_auth'")
    fun findKlazaConfigAuth(): ConfigPlugins

}