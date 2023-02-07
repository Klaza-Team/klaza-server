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
import klaza.klaza_server.configurations.EmailConfiguration
import klaza.klaza_server.data.EventData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
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

           this.sendEmail(userEmail, "Klaza - ${event.eventname}", event.toString())

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

            message.setText(text)

            Transport.send(message)

        }
        catch (e: Exception) {

            LOGGER.error(Colors.RED + "Error sending email to User: $email" + Colors.RESET)
            LOGGER.error(Colors.RED + e.message + Colors.RESET)

        }

    }

}