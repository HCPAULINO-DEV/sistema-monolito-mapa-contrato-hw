package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.service;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto.SaveDataUserDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.dto.ShowDataUserDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.User;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.UserRole;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    // CREATE
    public ShowDataUserDto createUser(SaveDataUserDto dto) {

        if (dto.role() == null) {
            throw new RuntimeException("Role é obrigatória");
        }

        // 🚫 BLOQUEIA criação de ADMIN
        if (dto.role() == UserRole.ADMIN) {
            throw new RuntimeException("Não é permitido criar ADMIN");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());

        repository.save(user);

        return new ShowDataUserDto(user);
    }

    // GET ALL
    public List<ShowDataUserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(ShowDataUserDto::new)
                .toList();
    }

    // DELETE
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}