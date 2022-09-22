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

    @PostConstruct
    fun start() {

//        LOGGER.info(Colors.GREEN_BOLD + userRepository.findAllByCourseID(3).toString() + Colors.RESET)
//        LOGGER.info(Colors.GREEN_BOLD + courseRepository.findAllByUserId(2) + Colors.RESET)

//        LOGGER.info(Colors.GREEN_BOLD + klazaAlertRepository.findAllByCourse_Id(3) + Colors.RESET)

//        LOGGER.info(Colors.GREEN_BOLD + configPluginsRepository.findKlazaConfigByName("version") + Colors.RESET)
//        LOGGER.info(Colors.GREEN_BOLD + configPluginsRepository.findAllKlazaConfig() + Colors.RESET)


    }

}