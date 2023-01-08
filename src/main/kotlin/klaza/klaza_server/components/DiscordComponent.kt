// Plugin Klaza para Moodle - Server - DiscordComponent.kt
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

package klaza.klaza_server.components

import discord4j.common.util.Snowflake
import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.spec.EmbedCreateSpec
import discord4j.discordjson.json.EmbedData
import discord4j.discordjson.json.EmbedFieldData
import klaza.klaza_server.classes.Colors
import klaza.klaza_server.configurations.DiscordConfiguration
import klaza.klaza_server.data.EventData
import klaza.klaza_server.models.KlazaDiscordInstanceModel
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

    @Autowired lateinit var discordConfiguration: DiscordConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Starting Discord client..." + Colors.RESET)

        client = DiscordClient.create(discordConfiguration.token)
        gateway = client.login().block()!!

        LOGGER.info(Colors.PURPLE + "Discord client started!" + Colors.RESET)

    }

    fun sendServerNotifications(event: EventData, instances: List<KlazaDiscordInstanceModel>) {

        for (i in instances) {

            try {

                val channel = client.getChannelById(Snowflake.of(i.channel!!))

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
                LOGGER.error(Colors.RED + "Error sending server notification to Guild: ${i.guild}, Channel: ${i.channel}, Event: ${event.eventname}" + Colors.RESET)
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