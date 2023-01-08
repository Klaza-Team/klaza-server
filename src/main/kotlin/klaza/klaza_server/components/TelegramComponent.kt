// Plugin Klaza para Moodle - Server - TelegramComponent.kt
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

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.SendMessage
import klaza.klaza_server.classes.Colors
import klaza.klaza_server.configurations.TelegramConfiguration
import klaza.klaza_server.data.EventData
import klaza.klaza_server.models.KlazaTelegramInstanceModel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class TelegramComponent {

    companion object {

        val LOGGER = LoggerFactory.getLogger(TelegramComponent::class.java)

        lateinit var bot: TelegramBot

    }

    @Autowired lateinit var telegramConfiguration: TelegramConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.PURPLE + "Starting Telegram client..." + Colors.RESET)

        bot = TelegramBot(telegramConfiguration.token)

        LOGGER.info(Colors.PURPLE + "Telegram client started!" + Colors.RESET)

    }

    fun sendServerNotifications(event: EventData, instances: List<KlazaTelegramInstanceModel>) {

        for (i in instances) {

            val resp = bot.execute(SendMessage(i.channel, event.toString()))

            if (!resp.isOk) {

                LOGGER.error(Colors.RED + "Error sending server notification to Channel: ${i.channel}, Event: ${event.eventname}" + Colors.RESET)
                LOGGER.error(Colors.RED + resp.message() + Colors.RESET)

            }

        }

    }

    fun sendUserNotification(event: EventData, userChatID: String, firstPriority: Boolean): Boolean {

        val resp = bot.execute(SendMessage(userChatID, event.toString()))

        if (resp.isOk) {
            return true
        }
        else {

            LOGGER.error(Colors.RED + "Error sending user notification to User: $userChatID, Event: ${event.eventname}" + Colors.RESET)
            LOGGER.error(Colors.RED + resp.message() + Colors.RESET)

            return false

        }

    }

}