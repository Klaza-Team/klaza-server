package klaza.klaza_server.components

import discord4j.core.DiscordClient
import org.springframework.stereotype.Component

@Component
class DiscordComponent {

    init {

        val client: DiscordClient = DiscordClient.create("token");



    }

}