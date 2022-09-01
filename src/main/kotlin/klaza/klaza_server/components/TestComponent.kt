package klaza.klaza_server.components

import klaza.klaza_server.configurations.KlazaConfiguration
import klaza.klaza_server.libs.Colors
import klaza.klaza_server.models.Quiz
import klaza.klaza_server.models.UserInfoData
import klaza.klaza_server.repositories.UserInfoDataRepository
import klaza.klaza_server.repositories.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestComponent {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TestComponent::class.java)
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userInfoDataRepository: UserInfoDataRepository

    @Autowired
    lateinit var KlazaConfiguration: KlazaConfiguration

    @PostConstruct
    fun start() {

        LOGGER.info(Colors.GREEN_BOLD + userRepository.findById(2).toString() + Colors.RESET)
        LOGGER.info(Colors.GREEN_BOLD + userInfoDataRepository.findById(4).toString() + Colors.RESET)

    }

}