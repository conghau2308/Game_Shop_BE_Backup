package com.A4Team.GamesShop.config;

import com.A4Team.GamesShop.filter.CustomSecurityFilter;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SercurityConfig {

    @Autowired
    private Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource,
            CustomSecurityFilter filter) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {

                    String[] publicUrls = env.getProperty("url.authUrl", "").split(",");
                    System.out.println("🟢 Public URLs: " + Arrays.toString(publicUrls));
                    for (String url : publicUrls) {
                        auth.requestMatchers(url.trim()).permitAll();
                    }
                    
                    auth.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll();

                    auth.requestMatchers("/auth/google/**").permitAll();
                    auth.requestMatchers("/api/games/**").permitAll();
                    auth.requestMatchers("/api/comments/**").permitAll();
                    auth.requestMatchers("/api/reviews/**").permitAll();
                    auth.requestMatchers("/api/games-prices/**").permitAll();
                    auth.requestMatchers("/api/orders/**").permitAll();
                    auth.requestMatchers("/api/payment/**").permitAll();
                    auth.requestMatchers("/api/payment/submitOrder").permitAll();
                    // auth.requestMatchers("/api/purchases/**").permitAll();
                    auth.requestMatchers("/api/order-purchases/**").permitAll();
                    auth.requestMatchers("/api/user/me", "/api/user/update", "/api/files/avatar",
                            "/api/user/change-email", "/api/user/change-password")
                            .hasAnyRole("USER", "ADMIN", "STAFF");
                    auth.requestMatchers("/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-resources/**",
                            "/webjars/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "https://game-shop-fe.vercel.app",
                "https://measured-dassie-fast.ngrok-free.app"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
