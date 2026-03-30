package com.projects.my.sistema_monolito_mapa_contrato_hw.configuration;

import com.projects.my.sistema_monolito_mapa_contrato_hw.filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(req -> req

                        // 🔓 ROTAS PÚBLICAS
                        .requestMatchers(
                                "/login",
                                "/",
                                "/index.html",
                                "/assets/**",
                                "/favicon.ico",
                                "/api/login"
                        ).permitAll()

                        // 👑 USERS → só ADMIN
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        // 📄 CONTRATOS
                        .requestMatchers(HttpMethod.GET, "/api/contratos/**")
                        .hasAnyRole("ADMIN", "VIEWER")

                        .requestMatchers(HttpMethod.POST, "/api/contratos/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/contratos/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/contratos/**")
                        .hasRole("ADMIN")

                        // 🔧 EQUIPAMENTOS
                        .requestMatchers(HttpMethod.GET, "/api/contratos/*/equipamentos/**")
                        .hasAnyRole("ADMIN", "VIEWER")

                        .requestMatchers(HttpMethod.POST, "/api/contratos/*/equipamentos/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/contratos/*/equipamentos/**")
                        .hasRole("ADMIN")

                        // 🔒 QUALQUER OUTRA → autenticado
                        .anyRequest().authenticated()
                )

                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}