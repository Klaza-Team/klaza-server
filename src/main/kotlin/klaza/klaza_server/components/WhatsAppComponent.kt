package klaza.klaza_server.components

import klaza.klaza_server.classes.Colors
import klaza.klaza_server.classes.Requests
import klaza.klaza_server.configurations.KlazaConfiguration
import klaza.klaza_server.data.EventData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class WhatsAppComponent {

    companion object {

        val LOGGER = LoggerFactory.getLogger(WhatsAppComponent::class.java)

    }

    @Autowired
    lateinit var configuration: KlazaConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Starting WhatsApp client..." + Colors.RESET)
        LOGGER.info(Colors.PURPLE + "WhatsApp client started!" + Colors.RESET)

    }

    fun sendUserNotification(event: EventData, userNumber: String, firstPriority: Boolean): Boolean {

       try {

           val body = """
            {
                "messaging_product": "whatsapp",
                "to": "$userNumber",
                "recipient_type": "individual",
                "type": "template",
                "template": {
                    "name": "teste",
                    "language": { "code": "pt_BR" },
                    "components": [{
            
                        "type": "body",
                        "parameters": [
                            {
                                "type": "text",
                                "text": "$event"
                            }
                        ]
            
                    }]
                }
            }
            """

           Requests("https://graph.facebook.com/v14.0/${configuration.whatsappNumberID}", configuration.whatsappToken).post("/messages", body)

           return true

       }
       catch (e: Exception) {

           LOGGER.error(Colors.RED + "Error sending user notification to User: $userNumber, Event: ${event.eventname}" + Colors.RESET)
           LOGGER.error(Colors.RED + e.message + Colors.RESET)

           return false
       }

    }

}