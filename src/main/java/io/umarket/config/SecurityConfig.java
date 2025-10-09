package io.umarket.config;

import io.umarket.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 🛑 1. ELIMINAMOS el campo y constructor para romper el ciclo.
    // private final CustomUserDetailsService customUserDetailsService;

    // public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
    //     this.customUserDetailsService = customUserDetailsService;
    // }

    @Bean
    // 🛠️ 2. AGREGAMOS el servicio como argumento. Spring lo inyectará automáticamente.
    public SecurityFilterChain filterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/registro", "/login",
                    "/css/**", "/js/**", "/images/**", "/favicon.ico"
                ).permitAll()
                .requestMatchers("/carrito", "/carrito/**").authenticated()
                .anyRequest().authenticated()
            )
            // 🛠️ 3. USAMOS el servicio inyectado en el argumento.
            .userDetailsService(customUserDetailsService) 
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}