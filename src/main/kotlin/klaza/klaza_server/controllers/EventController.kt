package klaza.klaza_server.controllers

import klaza.klaza_server.libs.Colors
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/event")
open class EventController {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(EventController::class.java)
    }

    @PostMapping("/test")
    open fun test() {

        LOGGER.info(Colors.BLUE + "aaaaaaaaa" + Colors.RESET)

    }

}