// Plugin Klaza para Moodle - Server - EmailComponent.kt
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
import klaza.klaza_server.configurations.AppConfiguration
import klaza.klaza_server.configurations.EmailConfiguration
import klaza.klaza_server.data.EventData
import klaza.klaza_server.services.CronService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.PostConstruct
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress

import javax.mail.internet.MimeMessage





@Component
class EmailComponent {

    companion object {

        val LOGGER = LoggerFactory.getLogger(EmailComponent::class.java)
        lateinit var session: Session

    }

    @Autowired lateinit var emailConfiguration: EmailConfiguration
    @Autowired lateinit var appConfiguration: AppConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Email Discord client..." + Colors.RESET)

        val props = Properties()

        props["mail.smtp.host"] = emailConfiguration.host;
        props["mail.transport.protocol"] = emailConfiguration.protocol;
        props["mail.smtp.socketFactory.port"] = emailConfiguration.port;
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory";
        props["mail.smtp.starttls.enable"] = emailConfiguration.starttls;
        props["mail.smtp.auth"] = emailConfiguration.auth;
        props["mail.smtp.port"] = emailConfiguration.port;
        props["mail.smtp.ssl.trust"] = emailConfiguration.host;
        props["mail.smtp.ssl.protocols"] = "TLSv1.2";

        session = Session.getDefaultInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(emailConfiguration.useremail, emailConfiguration.password)
            }
        })

//        session.setDebug(true)

        LOGGER.info(Colors.PURPLE + "Email client started!" + Colors.RESET)

    }

    fun sendUserNotification(event: EventData, userEmail: String) {

        try {

            println(userEmail)
            println("Klaza - ${event.eventname}")
            println(generateNotificationMessage(event))

            this.sendEmail(userEmail, "Klaza - ${event.eventname}", generateNotificationMessage(event))

        }
        catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending user notification to User: $userEmail, Event: ${event.eventname}" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

        }

    }



    fun sendEmail(email: String, subject: String, text: String) {

        try {

            val message: Message = MimeMessage(session)

            message.setFrom(InternetAddress(emailConfiguration.useremail, "Klaza"))

            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email)
            )

            message.subject = subject

            message.setContent(text, "text/html; charset=utf-8")

            Transport.send(message)

        }
        catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending email to User: $email" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

        }

    }



    fun generateNotificationMessage(event: EventData): String {

        var header = ""
        var message = ""
        val footer = "Klaza • KlazaTeam © Todos os direitos reservados."


        when (event.eventname) {

            "\\core\\event\\course_module_updated" -> {

                val url = "${appConfiguration.moodleBaseUrl}/mod/page/view.php?id=${event.objectid}"

                header = "<b> <a href='$url'> Klaza - Edição de conteúdo </a> </b>"

                message =
                    "Opa, a Klazy-chan detectou uma edição no Moodle!<br>" +
                            "Acesse o Moodle para ver o que foi alterado clicando <a href='$url'>aqui</a>."+
                            "<br><br>" +
                            "<b>\uD83C\uDF93 Nome do curso</b><br>" +
                            event.course?.fullname!! +
                            "<br><br>" +
                            "<b>\uD83D\uDC68\u200D\uD83C\uDFEB Professor</b><br>" +
                            event.user?.firstname + " " + event.user?.lastname +
                            "<br><br>" +
                            "<b>\uD83D\uDCD6 Conteúdo</b><br>" +
                            event.other.name!! +
                            "<br><br>" +
                            "<img src='https://cdn.discordapp.com/attachments/1016881985161019475/1072637624134664314/klazy.png'>"

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

        return "$header<br>$message<br>$footer"

    }

}