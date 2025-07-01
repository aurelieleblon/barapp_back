package com.barapp.barapp_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

 @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .cors(withDefaults())  // active CORS dans Spring Security
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/*").permitAll()
            .requestMatchers("/api/client/**").hasRole("CLIENT")
            .requestMatchers("/api/barmaker/**").hasRole("BARMAN")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
    .loginProcessingUrl("/api/login") // URL où le formulaire envoie les données
    .usernameParameter("username")
    .passwordParameter("password")
    .successHandler((request, response, authentication) -> {
        // Pas de redirection, on gère côté frontend
        response.setStatus(200);
    })
    .failureHandler((request, response, exception) -> {
        response.sendError(401, "Authentication Failed");
    })
    .permitAll());

    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

