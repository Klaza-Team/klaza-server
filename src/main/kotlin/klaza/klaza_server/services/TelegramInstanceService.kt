// Plugin Klaza para Moodle - Server - DiscordInstanceService.kt
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

package klaza.klaza_server.services

import klaza.klaza_server.dtos.TelegramInstanceDTO
import klaza.klaza_server.dtos.UserCourseConfigDTO
import klaza.klaza_server.models.KlazaTelegramInstanceConfigModel
import klaza.klaza_server.models.KlazaTelegramInstanceModel
import klaza.klaza_server.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TelegramInstanceService {

    @Autowired lateinit var telegramInstanceRepository: KlazaTelegramInstanceRepository
    @Autowired lateinit var klazaTelegramInstanceConfigRepository: KlazaTelegramInstanceConfigRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var userRepository: UserRepository

    fun getTelegramInstanceById(telegramInstanceId: Long): KlazaTelegramInstanceModel {
        return telegramInstanceRepository.findById(telegramInstanceId).orElseThrow { Exception("Telegram instance not found") }
    }

    fun getTelegramInstances(): List<KlazaTelegramInstanceModel> { return telegramInstanceRepository.findAll() }

    fun getDiscordInstancesDTO(): List<TelegramInstanceDTO> {

        return this.getTelegramInstances().map { instance ->
            val configs = klazaTelegramInstanceConfigRepository.findByTelegramInstance_Id(instance.id!!)
            instance.toDTO(UserCourseConfigDTO(configs!!))
        }

    }

    fun createTelegramInstance(telegramInstance: TelegramInstanceDTO, courseId: Long, creatorId: Long) {

        val course = courseRepository.findById(courseId).get()
        val creator = userRepository.findById(creatorId).get()

        val telegramInstanceModel = KlazaTelegramInstanceModel(
            null,
            telegramInstance.channel_id,
            course,
            creator
        )

        val telegramConfigModel = KlazaTelegramInstanceConfigModel(
            null,
            telegramInstanceModel,
            telegramInstance.config
        )

        telegramInstanceRepository.save(telegramInstanceModel)
        klazaTelegramInstanceConfigRepository.save(telegramConfigModel)

    }

    fun editTelegramInstance(telegramInstance: TelegramInstanceDTO) {

        telegramInstanceRepository.existsById(telegramInstance.id).let {
            if (it) {

                val discordInstanceModel = telegramInstanceRepository.findById(telegramInstance.id).get()
                discordInstanceModel.channel = telegramInstance.channel_id

                val instanceConfig = klazaTelegramInstanceConfigRepository.findByTelegramInstance_Id(telegramInstance.id)

                instanceConfig!!.useGlobal = telegramInstance.config.use_global
                instanceConfig.notifyCreateContent = telegramInstance.config.notify_create_content
                instanceConfig.notifyEditContent = telegramInstance.config.notify_edit_content
                instanceConfig.notifyDeleteContent = telegramInstance.config.notify_delete_content
                instanceConfig.notifyDeadline2Days = telegramInstance.config.notify_deadline_2_days
                instanceConfig.notifyDeadline1Day = telegramInstance.config.notify_deadline_1_day
                instanceConfig.notifyDeadline = telegramInstance.config.notify_deadline
                instanceConfig.notifySendAssignment = telegramInstance.config.notify_send_assignment
                instanceConfig.notifyReceiveMessage = telegramInstance.config.notify_receive_message
                instanceConfig.notifyReceiveComment = telegramInstance.config.notify_receive_comment
                instanceConfig.notifyDeleteComment = telegramInstance.config.notify_delete_comment

                telegramInstanceRepository.save(discordInstanceModel)
                klazaTelegramInstanceConfigRepository.save(instanceConfig)

            } else {
                throw Exception("Telegram instance not found")
            }
        }

    }

    fun deleteTelegramInstance(telegramInstanceId: Long) {
        telegramInstanceRepository.existsById(telegramInstanceId).let {
            if (it) {
                telegramInstanceRepository.deleteById(telegramInstanceId)
            } else {
                throw Exception("Discord instance not found")
            }
        }
    }

    fun getInstanceConfigDTO(telegramInstanceId: Long): UserCourseConfigDTO {
        val instanceConfig = klazaTelegramInstanceConfigRepository.findByTelegramInstance_Id(telegramInstanceId)
        return UserCourseConfigDTO(instanceConfig!!)
    }

}