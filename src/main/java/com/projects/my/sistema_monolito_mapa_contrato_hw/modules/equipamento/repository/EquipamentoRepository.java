package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.repository;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EquipamentoRepository extends JpaRepository<Equipamento, UUID> {

    List<Equipamento> findByContratoId(UUID contratoId);
}


