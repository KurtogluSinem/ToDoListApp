package de.htw.berlin.webtech.WebTech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{


        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**")
                    .allowedMethods("*")
                    .allowedOrigins(
                            "http://localhost:3003",
                            "https://github.com/Fatmaguel-Tokcan/todolistapp-frontend/tree/gh-pages"
                    );
        }

}


