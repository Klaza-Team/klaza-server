package klaza.klaza_server.security

import klaza.klaza_server.repositories.ConfigPluginsRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource



@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    lateinit var configPluginsRepository: ConfigPluginsRepository

    private val filter: ApiKeyAuthFilter = ApiKeyAuthFilter()
    private var managerSeted = false

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        if (!managerSeted) {
            filter.setAuthenticationManager(ApiKeyAuthManager(configPluginsRepository))
            managerSeted = true
        }

        http.csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(filter)

        return http.build()

    }

    // CORS configuration
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().applyPermitDefaultValues()
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}