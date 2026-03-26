package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.repository;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByUsername(String username);

}
