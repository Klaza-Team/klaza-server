package klaza.klaza_server.components

import discord4j.common.util.Snowflake
import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.spec.EmbedCreateSpec
import discord4j.discordjson.json.EmbedData
import discord4j.discordjson.json.EmbedFieldData
import discord4j.rest.entity.RestChannel
import klaza.klaza_server.classes.Colors
import klaza.klaza_server.configurations.KlazaConfiguration
import klaza.klaza_server.data.EventData
import klaza.klaza_server.models.KlazaDiscordInstance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class DiscordComponent {

    companion object {

        val LOGGER = LoggerFactory.getLogger(DiscordComponent::class.java)

        lateinit var client: DiscordClient
        lateinit var gateway: GatewayDiscordClient

    }

    @Autowired lateinit var configuration: KlazaConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Starting Discord client..." + Colors.RESET)

        client = DiscordClient.create(configuration.discordToken)
        gateway = client.login().block()!!

        LOGGER.info(Colors.PURPLE + "Discord client started!" + Colors.RESET)

    }

    fun sendServerNotifications(event: EventData, instances: List<KlazaDiscordInstance>) {

        for (i in instances) {

            try {

                val channel = client.getChannelById(Snowflake.of(i.getChannel()!!))

                val embed = EmbedData.builder()
                    .title("Notificação de Teste")
                    .addField(
                        EmbedFieldData.builder()
                            .name("To Testando")
                            .value(event.toString())
                            .build())
                    .build()

                channel.createMessage(embed).block()

            }
            catch (e: Exception) {
                LOGGER.error(Colors.RED + "Error sending server notification to Guild: ${i.getGuild()}, Channel: ${i.getChannel()}, Event: ${event.eventname}" + Colors.RESET)
                LOGGER.error(Colors.RED + e.message + Colors.RESET)
            }

        }

    }

    fun sendUserNotification(event: EventData, userID: String, firstPriority: Boolean): Boolean {

        try {

            val user = gateway.getUserById(Snowflake.of(userID)).block()!!

            val embed = EmbedCreateSpec.builder()
                .title("Notificação de Teste")
                .addField("To Testando", event.toString(), false)
                .build()

            user.privateChannel.block()!!.createMessage(embed)!!.block()

            return true

        }
        catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending user notification to User: $userID, Event: ${event.eventname}" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

            return false

        }

    }

}