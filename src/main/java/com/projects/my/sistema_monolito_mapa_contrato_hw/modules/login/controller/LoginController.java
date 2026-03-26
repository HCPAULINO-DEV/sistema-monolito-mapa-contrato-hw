package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.controller;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto.DataAuthenticationDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto.DataTokenJwt;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.User;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid DataAuthenticationDto dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = authenticationManager.authenticate(token);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJwt(tokenJWT));
    }
}
