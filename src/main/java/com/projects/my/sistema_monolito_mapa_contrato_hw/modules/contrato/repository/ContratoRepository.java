package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.repository;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {

    // Filtros existentes
    Page<Contrato> findByEmpresaContainingIgnoreCase(String empresa, Pageable pageable);

    Page<Contrato> findByCodErp(Integer codErp, Pageable pageable);

    Page<Contrato> findByEmpresaContainingIgnoreCaseAndCodErp(String empresa, Integer codErp, Pageable pageable);

    // NOVOS métodos para CNPJ
    Page<Contrato> findByCnpj(String cnpj, Pageable pageable);

    Page<Contrato> findByEmpresaContainingIgnoreCaseAndCnpj(String empresa, String cnpj, Pageable pageable);

    Page<Contrato> findByCodErpAndCnpj(Integer codErp, String cnpj, Pageable pageable);

    Page<Contrato> findByEmpresaContainingIgnoreCaseAndCodErpAndCnpj(String empresa, Integer codErp, String cnpj, Pageable pageable);
}