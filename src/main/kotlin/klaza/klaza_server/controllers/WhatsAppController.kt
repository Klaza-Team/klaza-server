package klaza.klaza_server.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/whatsapp")
class WhatsAppController {

    @RequestMapping("/webhook")
    fun webhook() {
        println("Hello World")
    }

}