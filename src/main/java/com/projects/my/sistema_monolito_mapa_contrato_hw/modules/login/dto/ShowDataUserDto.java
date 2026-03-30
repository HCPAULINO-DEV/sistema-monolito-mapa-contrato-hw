package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.User;

import java.util.UUID;

public record ShowDataUserDto(UUID id, String username) {
    public ShowDataUserDto(User user) {
        this(user.getId(), user.getUsername());
    }
}
