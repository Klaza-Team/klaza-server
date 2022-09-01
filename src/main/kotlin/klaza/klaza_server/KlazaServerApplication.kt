package klaza.klaza_server

import klaza.klaza_server.configurations.KlazaConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(KlazaConfiguration::class)
class KlazaServerApplication

fun main(args: Array<String>) {
	runApplication<KlazaServerApplication>(*args)
}