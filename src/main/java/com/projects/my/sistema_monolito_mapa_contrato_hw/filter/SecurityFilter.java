package com.projects.my.sistema_monolito_mapa_contrato_hw.filter;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.repository.UserRepository;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        // Ignora GET /login e POST /api/login
        if (path.equals("/login") || path.equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        var tokenJWT = retrieveToken(request);

        if (tokenJWT != null) {
            var subject = tokenService.extractSubject(tokenJWT);
            var user = userRepository.findByUsername(subject);

            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}