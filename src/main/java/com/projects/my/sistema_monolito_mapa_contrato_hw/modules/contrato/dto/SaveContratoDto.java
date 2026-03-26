package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.dto;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.PreventivaContrato;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.SituacaoContrato;

import java.util.UUID;

public record SaveContratoDto(
        UUID id,
        String codErp,
        String numeroContratoErp,
        String cnpj,
        String empresa,
        String cidade,
        String estado,
        SituacaoContrato situacao,
        String baseAtendimento,
        String equipeAtendimento,
        String equipamentosAtendidos,
        String particularidadeContrato,
        String slaAtendimentoContratado,
        PreventivaContrato preventiva,
        String corretiva,
        String laboratorio,
        String despesaViagemFrete,
        String pecas,
        String contato,
        String telefone,
        String email,
        String endereco,
        String executivoResponsavelDemandasComerciais
) {

}
