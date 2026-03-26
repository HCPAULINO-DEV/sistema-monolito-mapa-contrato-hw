package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.controller;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.dto.SaveEquipamentoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.dto.ShowDataEquipamentoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contratos/{contratoId}/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    // 🔹 LISTAR EQUIPAMENTOS DO CONTRATO
    @GetMapping
    public ResponseEntity<List<ShowDataEquipamentoDto>> list(
            @PathVariable UUID contratoId) {

        return ResponseEntity.ok(equipamentoService.getByContrato(contratoId));
    }

    // 🔹 CRIAR EQUIPAMENTO
    @PostMapping
    public ResponseEntity<ShowDataEquipamentoDto> create(
            @PathVariable UUID contratoId,
            @RequestBody @Valid SaveEquipamentoDto dto) {

        return ResponseEntity.ok(equipamentoService.save(contratoId, dto));
    }

    // 🔹 DELETAR EQUIPAMENTO
    @DeleteMapping("/{equipamentoId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID equipamentoId) {

        equipamentoService.delete(equipamentoId);
        return ResponseEntity.noContent().build();
    }
}