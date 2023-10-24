package com.example.todolist.configuration

import com.example.todolist.configuration.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfiguration(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {
    @Bean
    fun filterChain(http: HttpSecurity) = http
        .csrf { it.disable() }
        .headers {
            it.frameOptions { frameOptions ->
                frameOptions.sameOrigin()
            }
        }
        .authorizeHttpRequests {
            it.requestMatchers(
                "/api/v1/users",
                "/api/v1/users/signIn",
                "/swagger-ui/**",
                "/v3/**",
            ).permitAll()
                .anyRequest()
                .authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(STATELESS) }
        .build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
