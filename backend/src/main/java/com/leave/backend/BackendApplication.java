package com.leave.backend;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
//@ComponentScan(basePackages = "com.leave.backend.mappers") // Adjust the package name accordingly
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
    //   @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration corsConfiguration = new CorsConfiguration();
    //     corsConfiguration.addAllowedyyyOrigin("*");
    //     corsConfiguration.addAllowedHeader("*");
    //     corsConfiguration.addAllowedMethod("*");
    //     corsConfiguration.setExposedHeaders(List.of("x-auth-token"));
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**",corsConfiguration);
    //     return source;
    // }
}
