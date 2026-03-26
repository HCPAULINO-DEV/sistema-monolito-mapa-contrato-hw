package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.dto;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.Contrato;

import java.util.UUID;

public record ShowDataContratoDto(
        String id,
        String codErp,
        String numeroContratoErp,
        String cnpj,
        String empresa,
        String cidade,
        String estado,
        String situacao,
        String baseAtendimento,
        String equipeAtendimento,
        String equipamentosAtendidos,
        String particularidadeContrato,
        String slaAtendimentoContratado,
        String preventiva,
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

    public ShowDataContratoDto(Contrato contrato) {
        this(
                contrato.getId().toString(),
                contrato.getCodErp(),
                contrato.getNumeroContratoErp(),
                contrato.getCnpj(),
                contrato.getEmpresa(),
                contrato.getCidade(),
                contrato.getEstado(),
                contrato.getSituacao().toString(),
                contrato.getBaseAtendimento(),
                contrato.getEquipeAtendimento(),
                contrato.getEquipamentosAtendidos(),
                contrato.getParticularidadeContrato(),
                contrato.getSlaAtendimentoContratado(),
                contrato.getPreventiva().toString(),
                contrato.getCorretiva(),
                contrato.getLaboratorio(),
                contrato.getDespesaViagemFrete(),
                contrato.getPecas(),
                contrato.getContato(),
                contrato.getTelefone(),
                contrato.getEmail(),
                contrato.getEndereco(),
                contrato.getExecutivoResponsavelDemandasComerciais()
        );
    }
}
