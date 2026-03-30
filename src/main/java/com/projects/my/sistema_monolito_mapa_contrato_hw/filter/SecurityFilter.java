package com.projects.my.sistema_monolito_mapa_contrato_hw.filter;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.repository.UserRepository;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/login") || path.equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        var tokenJWT = retrieveToken(request);

        // 🔐 valida token antes
        if (tokenJWT != null && tokenService.isTokenValid(tokenJWT)) {

            var subject = tokenService.extractSubject(tokenJWT);
            var userOpt = userRepository.findByUsername(subject);

            if (userOpt.isPresent()) {
                var user = userOpt.get();

                var authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities() // 👈 ROLE vem daqui
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }
}