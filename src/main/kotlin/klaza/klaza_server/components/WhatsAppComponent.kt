// Plugin Klaza para Moodle - Server - WhatsAppComponent.kt
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

import klaza.klaza_server.classes.Colors
import klaza.klaza_server.classes.Requests
import klaza.klaza_server.configurations.AppConfiguration
import klaza.klaza_server.configurations.WhatsappConfiguration
import klaza.klaza_server.data.EventData
import klaza.klaza_server.models.UserModel
import klaza.klaza_server.services.CronService
import klaza.klaza_server.services.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.PostConstruct


@Component
class WhatsAppComponent {

    companion object {

        val LOGGER = LoggerFactory.getLogger(WhatsAppComponent::class.java)

    }

    @Autowired lateinit var whatsappConfiguration: WhatsappConfiguration
    @Autowired @Lazy lateinit var notificationService: NotificationService
    @Autowired lateinit var appConfiguration: AppConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Starting WhatsApp client..." + Colors.RESET)
        LOGGER.info(Colors.PURPLE + "WhatsApp client started!" + Colors.RESET)

    }

    fun sendUserNotification(event: EventData, userNumber: String, userModel: UserModel, firstPriority: Boolean): Boolean {

        if (notificationService.userHasNotificationEnabled(event, userModel)) {

            try {

                sendMessage(userNumber, event.toString())

                return true

            } catch (e: Exception) {

                LOGGER.error(Colors.RED + "Error sending user notification to User: $userNumber, Event: ${event.eventname}" + Colors.RESET)
                LOGGER.error(Colors.RED + e.message + Colors.RESET)

                return false
            }
        }
        else {
            return true
        }

    }



    fun sendMessage(userNumber: String, message: String): Boolean {

        try {

            val body = """
            {
                "messaging_product": "whatsapp",
                "to": "$userNumber",
                "recipient_type": "individual",
                "type": "template",
                "template": {
                    "name": "texto",
                    "language": { "code": "pt_BR" },
                    "components": [{
            
                        "type": "body",
                        "parameters": [
                            {
                                "type": "text",
                                "text": "$message"
                            }
                        ]
            
                    }]
                }
            }
            """

            Requests(
                "https://graph.facebook.com/v15.0/${whatsappConfiguration.numberID}",
                whatsappConfiguration.token
            ).post("/messages", body)

            return true

        } catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending message to User: $userNumber, Message: $message" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

            return false
        }

    }


    fun generateNotificationMessage(event: EventData): String {

        var header = ""
        var message = ""

        when (event.eventname) {

            "\\core\\event\\course_module_updated" -> {

                val url = "${appConfiguration.moodleBaseUrl}/mod/page/view.php?id=${event.objectid}"

                header = "<b> <a href='$url'> Klaza - Edição de conteúdo </a> </b>"

                message =
                    "Opa, a Klazy-chan detectou uma edição no Moodle!\n" +
                            "Acesse o Moodle para ver o que foi alterado clicando <a href='$url'>aqui</a>."+
                            "\n\n" +
                            "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                            event.course?.fullname!! +
                            "\n\n" +
                            "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                            event.user?.firstname + " " + event.user?.lastname +
                            "\n\n" +
                            "<b>\uD83D\uDCD6 Conteúdo</b>\n" +
                            event.other.name!! +
                            "\n\n" +
                            "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072637624134664314/klazy.png'>&#8205;</a>"

            }

            "\\core\\event\\course_module_created" -> {

                val url = "${appConfiguration.moodleBaseUrl}/mod/page/view.php?id=${event.objectid}"

                header = "<b> <a href='$url'> Klaza - Novo conteúdo </a> </b>"

                message =
                    "Opa, a Klazy-chan detectou um novo conteúdo no Moodle!\n" +
                            "Acesse o Moodle para ver o que foi adicionado clicando <a href='$url'>aqui</a>."+
                            "\n\n" +
                            "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                            event.course?.fullname!! +
                            "\n\n" +
                            "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                            event.user?.firstname + " " + event.user?.lastname +
                            "\n\n" +
                            "<b>\uD83D\uDCD6 Conteúdo</b>\n" +
                            event.other.name!! +
                            "\n\n" +
                            "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072637624134664314/klazy.png'>&#8205;</a>"

            }

            "\\core\\event\\course_module_deleted" -> {

                header = "<b> Klaza - Conteúdo deletado </b>"

                message =
                    "Opa, a Klazy-chan detectou que um conteúdo foi deletado no Moodle!" +
                            "\n\n" +
                            "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                            event.course?.fullname!! +
                            "\n\n" +
                            "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                            event.user?.firstname + " " + event.user?.lastname +
                            "\n\n" +
                            "<b>\uD83D\uDCD6 Conteúdo</b>\n" +
                            event.other.name!! +
                            "\n\n" +
                            "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072637624134664314/klazy.png'>&#8205;</a>"

            }

            else -> {

                when {

                    CronService.getCronNamePattern("Assign", "Open").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Tarefa aberta </a> </b>"

                        message =
                            "Opa, a Klazy-chan detectou uma tarefa aberta no Moodle!\n" +
                                    "Acesse o Moodle para ver a terefa clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCD6 Tarefa</b>\n" +
                                    event.relatedassign?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072637793567772732/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Assign", "Last2Days").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Tarefa 2 dias para entrega </a> </b>"

                        message =
                            "Ei esta faltando apenas 2 dias para finalizar a tarefa!\n" +
                                    "Acesse o Moodle para ver a terefa clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCD6 Tarefa</b>\n" +
                                    event.relatedassign?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072638280853635102/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Assign", "LastDay").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Tarefa ULTIMO DIA! </a> </b>"

                        message =
                            "Ei é o ultimo dia para finalizar a tarefa, corre lá!\n" +
                                    "Acesse o Moodle para ver a terefa clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCD6 Tarefa</b>\n" +
                                    event.relatedassign?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072638280853635102/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Assign", "Close").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/assign/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.allowsubmissionsfromdate!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedassign?.dueDate!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Tarefa Fechada </a> </b>"

                        message =
                            "Tarefa fechada, entregas não são mais aceitas!\n" +
                                    "Acesse o Moodle para ver a terefa clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCD6 Tarefa</b>\n" +
                                    event.relatedassign?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072639112709943366/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Quiz", "Open").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Prova aberta </a> </b>"

                        message =
                            "Opa, a Klazy-chan detectou uma prova para ser realizda no Moodle!\n" +
                                    "Acesse o Moodle para ver a prova clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCDD Prova</b>\n" +
                                    event.relatedquiz?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072637793567772732/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Quiz", "Last2Days").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Prova 2 dias para entrega </a> </b>"

                        message =
                            "Ei esta faltando apenas 2 dias para finalizar a prova!\n" +
                                    "Acesse o Moodle para ver a prova clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCDD Prova</b>\n" +
                                    event.relatedquiz?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072638280853635102/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Quiz", "LastDay").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Prova ULTIMO DIA! </a> </b>"

                        message =
                            "Ei é o ultimo dia para finalizar a prova, corre lá\n" +
                                    "Acesse o Moodle para ver a prova clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCDD Prova</b>\n" +
                                    event.relatedquiz?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072638280853635102/klazy.png'>&#8205;</a>"

                    }

                    CronService.getCronNamePattern("Quiz", "Close").matches(event.eventname) -> {

                        val url = "${appConfiguration.moodleBaseUrl}/mod/quiz/view.php?id=${event.objectid}"

                        val open =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeOpen!! * 1000))
                        val due =
                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(event.relatedquiz?.timeClose!! * 1000))

                        header = "<b> <a href='$url'> Klaza - Prova ULTIMO DIA! </a> </b>"

                        message =
                            "Prova fechada, entregas não são mais aceitas!\n" +
                                    "Acesse o Moodle para ver a prova clicando <a href='$url'>aqui</a>."+
                                    "\n\n" +
                                    "<b>\uD83C\uDF93 Nome do curso</b>\n" +
                                    event.course?.fullname!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b>\n" +
                                    event.user?.firstname + " " + event.user?.lastname +
                                    "\n\n" +
                                    "<b>\uD83D\uDCDD Prova</b>\n" +
                                    event.relatedquiz?.name!! +
                                    "\n\n" +
                                    "<b>\uD83D\uDD52 Abertura</b>\n" +
                                    open +
                                    "\n\n" +
                                    "<b>\uD83D\uDD58 Data de entrega</b>\n" +
                                    due +
                                    "\n\n" +
                                    "<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072639112709943366/klazy.png'>&#8205;</a>"

                    }

                }

            }

        }

        return "$header\n$message"

    }

}