package com.example.todolist.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS

@Configuration
class SecurityConfiguration {
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
                "/**",
                "/api/v1/users/**",
                "/swagger-ui/**",
                "/v3/**",
            ).permitAll()
                .anyRequest()
                .authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(STATELESS) }
        .build()
}
