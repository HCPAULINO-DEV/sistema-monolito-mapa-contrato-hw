package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.service;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.Contrato;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.repository.ContratoRepository;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.dto.SaveEquipamentoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.dto.ShowDataEquipamentoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.entity.Equipamento;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    private Equipamento findById(UUID id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
    }

    private Contrato findContrato(UUID contratoId) {
        return contratoRepository.findById(contratoId)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    // 🔹 LISTAR EQUIPAMENTOS DO CONTRATO
    public List<ShowDataEquipamentoDto> getByContrato(UUID contratoId) {
        return equipamentoRepository.findByContratoId(contratoId)
                .stream()
                .map(ShowDataEquipamentoDto::new)
                .toList();
    }

    // 🔹 CRIAR EQUIPAMENTO PARA UM CONTRATO
    public ShowDataEquipamentoDto save(UUID contratoId, SaveEquipamentoDto dto) {

        Contrato contrato = findContrato(contratoId);

        Equipamento eq = new Equipamento();
        eq.setCodigoInterno(dto.codigoInterno());
        eq.setTipoEquipamento(dto.tipoEquipamento());
        eq.setFabricante(dto.fabricante());
        eq.setDescricao(dto.descricao());
        eq.setLocal(dto.local());
        eq.setIdentificacaoRede(dto.identificacaoRede());
        eq.setContrato(contrato);

        equipamentoRepository.save(eq);

        return new ShowDataEquipamentoDto(eq);
    }

    // 🔹 DELETAR EQUIPAMENTO
    public void delete(UUID id) {
        equipamentoRepository.delete(findById(id));
    }
}