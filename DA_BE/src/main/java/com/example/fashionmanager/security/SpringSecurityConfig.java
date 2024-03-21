package com.example.fashionmanager.security;

import com.example.fashionmanager.jwt.JwtAuthenticationFilter;
import com.example.fashionmanager.jwt.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UnauthorizedHandler unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable).cors(httpSecurityCorsConfigurer -> {
                    httpSecurityCorsConfigurer.configurationSource(request -> {
                        var cors = new CorsConfiguration();
                        cors.setAllowedOrigins(List.of("http://localhost:3000"));
                        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                        cors.setAllowedHeaders(List.of("*"));
                        return cors;
                    });
                })
                .authorizeHttpRequests(o ->
                        o
                                .requestMatchers("admin/san-pham/**")
                                .hasAnyAuthority("ROLE_ADMIN_PRODUCT", "ROLE_SUPER_ADMIN")

                                .requestMatchers("admin/su-kien/**",
                                        "admin/san-pham/quan-ly-san-pham/list",
                                        "admin/san-pham/quan-ly-san-pham/detail/**",
                                        "admin/san-pham/quan-ly-danh-muc/list")
                                .hasAnyAuthority("ROLE_ADMIN_MAKETING", "ROLE_SUPER_ADMIN")

                                .requestMatchers("super-admin/**").hasAuthority("ROLE_SUPER_ADMIN")
                                .requestMatchers("admin/**").hasAuthority("ROLE_SUPER_ADMIN")
                                .requestMatchers("auth/authenticate", "auth/refresh-token").permitAll()
                                .requestMatchers("user/**").permitAll()
                );
        security.exceptionHandling(o -> o.authenticationEntryPoint(unauthorizedHandler));
        security.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
