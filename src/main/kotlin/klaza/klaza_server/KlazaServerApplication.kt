package klaza.klaza_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KlazaServerApplication

fun main(args: Array<String>) {
	runApplication<KlazaServerApplication>(*args)
}
