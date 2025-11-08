package io.umarket.admin;

import io.umarket.model.Usuario;
import io.umarket.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByUserName("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUserName("admin");
                admin.setEmail("admin@umarket.com");
                admin.setUserPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ADMIN");

                usuarioRepository.save(admin);
                System.out.println("Usuario admin creado: admin / admin123");
            } else {
                System.out.println("Usuario admin ya existe, no se creó otro.");
            }
        };
    }
}
