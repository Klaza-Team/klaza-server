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
import discord4j.core.spec.MessageCreateSpec
import discord4j.discordjson.json.EmbedData
import discord4j.discordjson.json.EmbedFieldData
import discord4j.discordjson.json.ImmutableEmbedData
import discord4j.rest.util.Color
import klaza.klaza_server.classes.Colors
import klaza.klaza_server.classes.KlazyImages
import klaza.klaza_server.configurations.AppConfiguration
import klaza.klaza_server.configurations.DiscordConfiguration
import klaza.klaza_server.data.EventData
import klaza.klaza_server.models.KlazaDiscordInstanceModel
import klaza.klaza_server.models.UserModel
import klaza.klaza_server.services.CronService
import klaza.klaza_server.services.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.annotation.PostConstruct


@Component
class DiscordComponent {

    companion object {

        val LOGGER = LoggerFactory.getLogger(DiscordComponent::class.java)

        lateinit var client: DiscordClient
        lateinit var gateway: GatewayDiscordClient

    }

    @Autowired lateinit var discordConfiguration: DiscordConfiguration
    @Autowired @Lazy lateinit var notificationService: NotificationService
    @Autowired lateinit var appConfiguration: AppConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Starting Discord client..." + Colors.RESET)

        client = DiscordClient.create(discordConfiguration.token)
        gateway = client.login().block()!!

        LOGGER.info(Colors.PURPLE + "Discord client started!" + Colors.RESET)

    }

    fun sendServerNotifications(event: EventData, instances: List<KlazaDiscordInstanceModel>) {

        for (i in instances) {

            if (notificationService.discordInstanceHasNotificationEnabled(event, i)) {

                try {

                   this.sendDiscordMessage(i.channel!!, this.generateNotificationMessage(event))

                }
                catch (e: Exception) {
                    LOGGER.error(Colors.RED + "Error sending server notification to Guild: ${i.guild}, Channel: ${i.channel}, Event: ${event.eventname}" + Colors.RESET)
                    LOGGER.error(Colors.RED + e.message + Colors.RESET)
                }
            }

        }

    }

    fun sendUserNotification(event: EventData, discordId: String, userModel: UserModel, firstPriority: Boolean): Boolean {

        if (notificationService.userHasNotificationEnabled(event, userModel)) {
            try {

                this.sendDiscordMessage(discordId, this.generateNotificationMessage(event))

                return true

            }
            catch (e: Exception) {

                LOGGER.error(Colors.RED + "Error sending user notification to User: $userModel, Event: ${event.eventname}" + Colors.RESET)
                LOGGER.error(Colors.RED + e.message + Colors.RESET)

                return false

            }
        }
        else {
            return true
        }

    }



    fun sendDiscordUserMessage(discordId: String, message: MessageCreateSpec): Boolean {

        try {

            val user = gateway.getUserById(Snowflake.of(discordId)).block()!!

            user.privateChannel.block()!!.createMessage(message)!!.block()

            return true

        }
        catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending user message to User: $discordId" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

            return false

        }

    }

    fun sendDiscordUserEmbed(discordId: String, embed: EmbedCreateSpec): Boolean {

        try {

            val user = gateway.getUserById(Snowflake.of(discordId)).block()!!

            user.privateChannel.block()!!.createMessage(embed)!!.block()

            return true

        }
        catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending user embed to User: $discordId" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

            return false

        }

    }

    fun sendDiscordMessage(channelId: String, message: MessageCreateSpec) {
        try {
            val channel = client.getChannelById(Snowflake.of(channelId))
            channel.createMessage(message.asRequest()).block()
        }
        catch (e: Exception) {
            LOGGER.error(Colors.RED + "Error sending message to Channel: $channelId" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)
        }
    }

    fun sendDiscordEmbed(channelId: String, embed: EmbedCreateSpec) {
        try {
            val channel = client.getChannelById(Snowflake.of(channelId))
            channel.createMessage(embed.asRequest()).block()
        }
        catch (e: Exception) {
            LOGGER.error(Colors.RED + "Error sending embed to Channel: $channelId" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)
        }
    }



    fun generateNotificationMessage(event: EventData): MessageCreateSpec {

        val embed = EmbedCreateSpec.builder()
        val message = MessageCreateSpec.builder()

        embed
            .color(Color.of(0x457D58))
            .thumbnail("attachment://klaza.png")
            .image("attachment://klazy.png")

        when (event.eventname) {

            "\\core\\event\\course_module_updated" -> {

                val url = "${appConfiguration.moodleBaseUrl}/mod/page/view.php?id=${event.objectid}"

                embed
                    .title("Klaza - Edição de conteúdo")
                    .description("Opa, a Klazy-chan detectou uma edição no Moodle!\nAcesse o Moodle para ver o que foi alterado clicando [aqui]($url).\n")
                    .url(url)

                    .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                    .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                    .addField("\uD83D\uDCD6 Conteúdo", event.other.name!!, false)

                message.addFile("klazy.png", KlazyImages.getEscrevendo().inputStream())

            }

            "\\core\\event\\course_module_created" -> {

                val url = "${appConfiguration.moodleBaseUrl}/mod/page/view.php?id=${event.objectid}"

                embed
                    .title("Klaza - Novo conteúdo")
                    .description("Opa, a Klazy-chan detectou um novo conteúdo no Moodle!\nAcesse o Moodle para ver o que foi adicionado clicando [aqui]($url).\n")
                    .url(url)

                    .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                    .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                    .addField("\uD83D\uDCD6 Conteúdo", event.other.name!!, false)

                message.addFile("klazy.png", KlazyImages.getEscrevendo().inputStream())

            }

            "\\core\\event\\course_module_deleted" -> {

                embed
                    .title("Klaza - Conteúdo deletado")
                    .description("Opa, a Klazy-chan detectou que um conteúdo foi deletado no Moodle!\n")

                    .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                    .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                    .addField("\uD83D\uDCD6 Conteúdo", event.other.name!!, false)

                message.addFile("klazy.png", KlazyImages.getEscrevendo().inputStream())

            }

            else -> {

                when {

                    CronService.getCronNamePattern("Assign", "Open").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        embed
                            .title("Klaza - Tarefa aberta")
                            .description("Opa, a Klazy-chan detectou uma tarefa aberta no Moodle!\nAcesse o Moodle para ver a terefa clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCD2 Tarefa", event.relatedassign?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getMegafone().inputStream())

                    }

                    CronService.getCronNamePattern("Assign", "Last2Days").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        embed
                            .title("Klaza - Tarefa 2 dias para entrega")
                            .description("Ei esta faltando apenas 2 dias para finalizar a tarefa!\nAcesse o Moodle para ver a terefa clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCD2 Tarefa", event.relatedassign?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getRelogio().inputStream())

                    }

                    CronService.getCronNamePattern("Assign", "LastDay").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        embed
                            .title("Klaza - Tarefa ULTIMO DIA!")
                            .description("Ei é o ultimo dia para finalizar a tarefa, corre lá!\nAcesse o Moodle para ver a terefa clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCD2 Tarefa", event.relatedassign?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getRelogio().inputStream())

                    }

                    CronService.getCronNamePattern("Assign", "Close").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        embed
                            .title("Klaza - Tarefa Fechada")
                            .description("Tarefa fechada, entregas não são mais aceitas!\nAcesse o Moodle para ver a terefa clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCD2 Tarefa", event.relatedassign?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getPapeis().inputStream())

                    }

                    CronService.getCronNamePattern("Quiz", "Open").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        embed
                            .title("Klaza - Prova aberta")
                            .description("Opa, a Klazy-chan detectou uma prova para ser realizda no Moodle!\nAcesse o Moodle para ver a prova clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCDD Prova", event.relatedquiz?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getMegafone().inputStream())

                    }

                    CronService.getCronNamePattern("Quiz", "Last2Days").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        embed
                            .title("Klaza - Prova 2 dias para entrega")
                            .description("Ei esta faltando apenas 2 dias para finalizar a prova!\nAcesse o Moodle para ver a prova clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCDD Prova", event.relatedquiz?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getRelogio().inputStream())

                    }

                    CronService.getCronNamePattern("Quiz", "LastDay").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        embed
                            .title("Klaza - Prova ULTIMO DIA!")
                            .description("Ei é o ultimo dia para finalizar a prova, corre lá!\nAcesse o Moodle para ver a prova clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", event.user?.firstname + " " + event.user?.lastname, false)
                            .addField("\uD83D\uDCDD Prova", event.relatedquiz?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getRelogio().inputStream())

                    }

                    CronService.getCronNamePattern("Quiz", "Close").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        embed
                            .title("Klaza - Prova Fechada!")
                            .description("Prova fechada, entregas não são mais aceitas!\nAcesse o Moodle para ver a prova clicando [aqui]($url).\n")
                            .url(url)

                            .addField("\uD83C\uDF93 Nome do curso", event.course?.fullname!!, false)
                            .addField(
                                "\uD83D\uDC68\u200D\uD83C\uDFEB Professor",
                                event.user?.firstname + " " + event.user?.lastname,
                                false
                            )
                            .addField("\uD83D\uDCDD Prova", event.relatedquiz?.name!!, false)
                            .addField("️\uD83D\uDD52 Abertura", open, false)
                            .addField("\uD83D\uDD58 ️Data de entrega", due, false)

                        message.addFile("klazy.png", KlazyImages.getPapeis().inputStream())

                    }

                }

            }

        }

        embed
            .footer("Klaza • KlazaTeam © Todos os direitos reservados.", "")
            .timestamp(java.time.Instant.now())

        message
            .addFile("klaza.png", TestComponent::class.java.getResource("/images/logo.png")?.readBytes()?.inputStream()!!)
            .addEmbed(embed.build())

        return message.build()

    }

}