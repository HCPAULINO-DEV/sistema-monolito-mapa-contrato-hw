package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.UserRole;
import jakarta.validation.constraints.NotBlank;

public record SaveDataUserDto(

        @NotBlank
        String username,

        @NotBlank
        String password,

        UserRole role
) {
}