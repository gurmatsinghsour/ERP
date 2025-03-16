package com.example.demo;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecureConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1) Authorize requests:
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/protected/**").authenticated()
                .anyRequest().permitAll()
            )

            // 2) Exception handling:
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    // ✅ Print to console (or log)
                    System.out.println("⚠️ Unauthorized access attempt to: " + request.getRequestURL());
                    
                    // You can also log (better for production)
                    // LoggerFactory.getLogger(SecureConfig.class).warn("Unauthorized access attempt to: {}", request.getRequestURL());

                    // ✅ Perform redirect to Authelia
                    String autheliaLoginUrl = "https://auth.example.com?rd="
                        + URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8);
                    // response.sendRedirect(autheliaLoginUrl);
                })
            )

            // 3) Disable default login/logout pages (optional)
            .formLogin(login -> login.disable())
            .logout(logout -> logout.disable())

            // 4) Disable CSRF if not needed
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
