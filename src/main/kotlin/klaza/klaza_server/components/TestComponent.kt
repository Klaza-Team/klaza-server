package klaza.klaza_server.components

import klaza.klaza_server.configurations.KlazaConfiguration
import klaza.klaza_server.libs.Colors
import klaza.klaza_server.models.Quiz
import klaza.klaza_server.models.UserInfoData
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

    @Autowired lateinit var KlazaConfiguration: KlazaConfiguration

    @PostConstruct
    fun start() {

//        LOGGER.info(Colors.GREEN_BOLD + userRepository.findAllByCourseID(3).toString() + Colors.RESET)
//        LOGGER.info(Colors.GREEN_BOLD + courseRepository.findAllByUserId(2) + Colors.RESET)

//        LOGGER.info(Colors.GREEN_BOLD + klazaAlertRepository.findAllByCourse_Id(3) + Colors.RESET)

//        LOGGER.info(Colors.GREEN_BOLD + configPluginsRepository.findKlazaConfigByName("version") + Colors.RESET)
//        LOGGER.info(Colors.GREEN_BOLD + configPluginsRepository.findAllKlazaConfig() + Colors.RESET)


    }

}