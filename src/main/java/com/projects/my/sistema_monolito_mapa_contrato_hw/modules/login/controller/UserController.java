package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.controller;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto.SaveDataUserDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto.ShowDataUserDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // POST - cadastrar usuário
    @PostMapping
    public ShowDataUserDto create(@RequestBody @Valid SaveDataUserDto dto) {
        return service.createUser(dto);
    }

    // GET - listar usuários
    @GetMapping
    public List<ShowDataUserDto> getAll() {
        return service.getAllUsers();
    }

    // DELETE - deletar usuário por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteUser(id);
    }
}