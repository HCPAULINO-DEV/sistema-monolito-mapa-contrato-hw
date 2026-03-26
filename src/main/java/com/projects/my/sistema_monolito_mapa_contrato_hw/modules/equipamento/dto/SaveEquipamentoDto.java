package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.dto;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.entity.TipoEquipamento;

public record SaveEquipamentoDto(
        String codigoInterno,
        TipoEquipamento tipoEquipamento,
        String fabricante,
        String descricao,
        String local,
        String identificacaoRede
) {
}
