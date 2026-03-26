package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.dto;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.entity.Equipamento;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.entity.TipoEquipamento;

import java.util.UUID;

public record ShowDataEquipamentoDto(
        UUID id,
        String codigoInterno,
        TipoEquipamento tipoEquipamento,
        String fabricante,
        String descricao,
        String local,
        String identificacaoRede
) {
    public ShowDataEquipamentoDto(Equipamento equipamento) {
        this(
                equipamento.getId(),
                equipamento.getCodigoInterno(),
                equipamento.getTipoEquipamento(),
                equipamento.getFabricante(),
                equipamento.getDescricao(),
                equipamento.getLocal(),
                equipamento.getIdentificacaoRede()
        );
    }

}
