package io.umarket.config; 

import io.umarket.model.Carrito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class WebConfig {

    /**
     * Define un Bean de Carrito con alcance de Sesión.
     * Spring crea una instancia de Carrito para cada usuario y la guarda en su sesión.
     * La inyectará automáticamente donde se pida (@SessionAttribute("carrito")).
     */
    @Bean
    @SessionScope
    public Carrito carrito() {
        return new Carrito();
    }
}