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

import klaza.klaza_server.dtos.DiscordInstanceDTO
import klaza.klaza_server.dtos.TelegramInstanceDTO
import klaza.klaza_server.dtos.UserCourseConfigDTO
import klaza.klaza_server.models.CourseModel
import klaza.klaza_server.models.KlazaDiscordInstanceConfigModel
import klaza.klaza_server.models.KlazaDiscordInstanceModel
import klaza.klaza_server.models.KlazaTelegramInstanceModel
import klaza.klaza_server.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DiscordInstanceService {

    @Autowired lateinit var discordInstanceRepository: KlazaDiscordInstanceRepository
    @Autowired lateinit var klazaDiscordInstanceConfigRepository: KlazaDiscordInstanceConfigRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var userRepository: UserRepository

    fun getDiscordInstanceById(discordInstanceId: Long): KlazaDiscordInstanceModel {
        return discordInstanceRepository.findById(discordInstanceId).orElseThrow { Exception("Discord instance not found") }
    }

    fun getDiscordInstances(): List<KlazaDiscordInstanceModel> { return discordInstanceRepository.findAll() }

    fun getDiscordInstancesDTO(): List<DiscordInstanceDTO> {

            return this.getDiscordInstances().map { instance ->
                val configs = klazaDiscordInstanceConfigRepository.findByDiscordInstance_Id(instance.id!!)
                instance.toDTO(UserCourseConfigDTO(configs!!))
            }

        }

    fun createDiscordInstance(discordInstance: DiscordInstanceDTO, courseId: Long, creatorId: Long) {

        val course = courseRepository.findById(courseId).get()
        val creator = userRepository.findById(creatorId).get()

        val discordInstanceModel = KlazaDiscordInstanceModel(
            null,
            discordInstance.guild_id,
            discordInstance.channel_id,
            course,
            creator
        )

        val discordConfigModel = KlazaDiscordInstanceConfigModel(
            null,
            discordInstanceModel,
            discordInstance.config
        )

        discordInstanceRepository.save(discordInstanceModel)
        klazaDiscordInstanceConfigRepository.save(discordConfigModel)

    }

    fun editDiscordInstance(discordInstance: DiscordInstanceDTO) {

        discordInstanceRepository.existsById(discordInstance.id).let {
            if (it) {

                val discordInstanceModel = discordInstanceRepository.findById(discordInstance.id).get()
                discordInstanceModel.channel = discordInstance.channel_id
                discordInstanceModel.guild = discordInstance.guild_id

                val instanceConfig = klazaDiscordInstanceConfigRepository.findByDiscordInstance_Id(discordInstance.id)

                instanceConfig!!.useGlobal = discordInstance.config.use_global
                instanceConfig.notifyCreateContent = discordInstance.config.notify_create_content
                instanceConfig.notifyEditContent = discordInstance.config.notify_edit_content
                instanceConfig.notifyDeleteContent = discordInstance.config.notify_delete_content
                instanceConfig.notifyDeadline2Days = discordInstance.config.notify_deadline_2_days
                instanceConfig.notifyDeadline1Day = discordInstance.config.notify_deadline_1_day
                instanceConfig.notifyDeadline = discordInstance.config.notify_deadline
                instanceConfig.notifySendAssignment = discordInstance.config.notify_send_assignment
                instanceConfig.notifyReceiveMessage = discordInstance.config.notify_receive_message
                instanceConfig.notifyReceiveComment = discordInstance.config.notify_receive_comment
                instanceConfig.notifyDeleteComment = discordInstance.config.notify_delete_comment

                discordInstanceRepository.save(discordInstanceModel)
                klazaDiscordInstanceConfigRepository.save(instanceConfig)

            } else {
                throw Exception("Discord instance not found")
            }
        }

    }

    fun deleteDiscordInstance(discordInstanceId: Long) {
        discordInstanceRepository.existsById(discordInstanceId).let {
            if (it) {
                discordInstanceRepository.deleteById(discordInstanceId)
            } else {
                throw Exception("Discord instance not found")
            }
        }
    }

    fun getInstanceConfigDTO(discordInstanceId: Long): UserCourseConfigDTO {
        val instanceConfig = klazaDiscordInstanceConfigRepository.findByDiscordInstance_Id(discordInstanceId)
        return UserCourseConfigDTO(instanceConfig!!)
    }

}