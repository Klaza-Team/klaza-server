// Plugin Klaza para Moodle - Server - TestComponent.kt
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

import discord4j.core.spec.EmbedCreateSpec
import discord4j.core.spec.MessageCreateSpec
import discord4j.rest.util.Color
import klaza.klaza_server.classes.KlazyImages
import klaza.klaza_server.configurations.AppConfiguration
import klaza.klaza_server.repositories.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestComponent {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TestComponent::class.java)
    }

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var userInfoDataRepository: UserInfoDataRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var klazaAlertRepository: KlazaAlertRepository
    @Autowired lateinit var configPluginsRepository: ConfigPluginsRepository

    @Autowired lateinit var emailComponent: EmailComponent
    @Autowired lateinit var discordComponent: DiscordComponent
    @Autowired lateinit var telegramComponent: TelegramComponent
    @Autowired lateinit var whatsAppComponent: WhatsAppComponent

    @Autowired lateinit var appConfiguration: AppConfiguration

    @PostConstruct
    fun start() {
        LOGGER.info("TestComponent...")

        println(appConfiguration.moodleBaseUrl)

//        this.testDiscordNotification()
//        this.testTelegramNotification()
//        this.testWhatsAppNotification()
        this.testEmailNotification()

    }

    fun testDiscordNotification() {

        val embed = EmbedCreateSpec.builder()

            .title("Klaza - Edição de conteúdo")
            .description("Opa, a Klazy-chan detectou uma edição no Moodle!\n" + "Acesse o Moodle para ver o que foi alterado clicando [aqui](http://localhost/www/moodle-400-klaza/moodle/mod/page/view.php?id=27).\n   ")
            .url("http://localhost/www/moodle-400-klaza/moodle/mod/page/view.php?id=27")
            .color(Color.of(0x457D58))
            .image("attachment://klazy.png")
            .thumbnail("attachment://klaza.png")

            .addField("\uD83C\uDF93 Nome do curso", "Teste", false)
            .addField("\uD83D\uDC68\u200D\uD83C\uDFEB Professor", "Admin User", false)
            .addField("\uD83D\uDCD6 Conteúdo", "Página Teste", false)

            .footer("Klaza • KlazaTeam © Todos os direitos reservados.", "")
            .timestamp(java.time.Instant.now())

            .build()

        val message = MessageCreateSpec.builder()
            .addFile("klazy.png", KlazyImages.getEscrevendo().inputStream())
            .addFile("klaza.png", TestComponent::class.java.getResource("/images/logo.png")?.readBytes()?.inputStream()!!)
            .addEmbed(embed)
            .build()

        discordComponent.sendDiscordMessage("1016881985161019475", message)


    }

    fun testTelegramNotification() {

//        println(KlazyImages.getAcenandoURL())

        telegramComponent.sendMessage("5255557468", "<b>Teste</b>\n<a href='https://cdn.discordapp.com/attachments/1016881985161019475/1072637624134664314/klazy.png'>&#8205;</a>")

    }

    fun testEmailNotification() {

        emailComponent.sendEmail("emanuel.souza.scherer@gmail.com", "Teste", "Teste")

    }

}