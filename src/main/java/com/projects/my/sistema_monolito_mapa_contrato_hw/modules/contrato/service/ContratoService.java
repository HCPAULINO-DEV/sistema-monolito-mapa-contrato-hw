package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.service;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.dto.SaveContratoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.dto.ShowDataContratoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.Contrato;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.repository.ContratoRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    private Contrato findContratoById(UUID id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato nao encontrado"));
    }

    public ShowDataContratoDto saveContrato(@Valid SaveContratoDto dto) {
        Contrato contrato = new Contrato();

        contrato.setCodErp(dto.codErp());
        contrato.setNumeroContratoErp(dto.numeroContratoErp());
        contrato.setCnpj(dto.cnpj());
        contrato.setEmpresa(dto.empresa());
        contrato.setCidade(dto.cidade());
        contrato.setEstado(dto.estado());
        contrato.setSituacao(dto.situacao());
        contrato.setBaseAtendimento(dto.baseAtendimento());
        contrato.setEquipeAtendimento(dto.equipeAtendimento());
        contrato.setEquipamentosAtendidos(dto.equipamentosAtendidos());
        contrato.setParticularidadeContrato(dto.particularidadeContrato());
        contrato.setSlaAtendimentoContratado(dto.slaAtendimentoContratado());
        contrato.setPreventiva(dto.preventiva());
        contrato.setCorretiva(dto.corretiva());
        contrato.setLaboratorio(dto.laboratorio());
        contrato.setDespesaViagemFrete(dto.despesaViagemFrete());
        contrato.setPecas(dto.pecas());
        contrato.setContato(dto.contato());
        contrato.setTelefone(dto.telefone());
        contrato.setEmail(dto.email());
        contrato.setEndereco(dto.endereco());
        contrato.setExecutivoResponsavelDemandasComerciais(dto.executivoResponsavelDemandasComerciais());

        contratoRepository.save(contrato);

        return new ShowDataContratoDto(contrato);
    }

    public Page<ShowDataContratoDto> getContratos(String nome, Integer codErp, String cnpj, Pageable pageable) {
        Page<Contrato> contratos;

        if (nome != null && codErp != null && cnpj != null) {
            contratos = contratoRepository
                    .findByEmpresaContainingIgnoreCaseAndCodErpAndCnpj(nome, codErp, cnpj, pageable);

        } else if (nome != null && codErp != null) {
            contratos = contratoRepository
                    .findByEmpresaContainingIgnoreCaseAndCodErp(nome, codErp, pageable);

        } else if (nome != null && cnpj != null) {
            contratos = contratoRepository
                    .findByEmpresaContainingIgnoreCaseAndCnpj(nome, cnpj, pageable);

        } else if (codErp != null && cnpj != null) {
            contratos = contratoRepository
                    .findByCodErpAndCnpj(codErp, cnpj, pageable);

        } else if (nome != null) {
            contratos = contratoRepository
                    .findByEmpresaContainingIgnoreCase(nome, pageable);

        } else if (codErp != null) {
            contratos = contratoRepository
                    .findByCodErp(codErp, pageable);

        } else if (cnpj != null) {
            contratos = contratoRepository
                    .findByCnpj(cnpj, pageable);

        } else {
            contratos = contratoRepository.findAll(pageable);
        }

        return contratos.map(ShowDataContratoDto::new);
    }

    public ShowDataContratoDto updateContrato(UUID id, SaveContratoDto dto) {

        Contrato contrato = findContratoById(id);

        if (dto.codErp() != null) {
            contrato.setCodErp(dto.codErp());
        }

        if (dto.numeroContratoErp() != null) {
            contrato.setNumeroContratoErp(dto.numeroContratoErp());
        }

        if (dto.cnpj() != null) {
            contrato.setCnpj(dto.cnpj());
        }

        if (dto.empresa() != null) {
            contrato.setEmpresa(dto.empresa());
        }

        if (dto.cidade() != null) {
            contrato.setCidade(dto.cidade());
        }

        if (dto.estado() != null) {
            contrato.setEstado(dto.estado());
        }

        if (dto.situacao() != null) {
            contrato.setSituacao(dto.situacao());
        }

        if (dto.baseAtendimento() != null) {
            contrato.setBaseAtendimento(dto.baseAtendimento());
        }

        if (dto.equipeAtendimento() != null) {
            contrato.setEquipeAtendimento(dto.equipeAtendimento());
        }

        if (dto.equipamentosAtendidos() != null) {
            contrato.setEquipamentosAtendidos(dto.equipamentosAtendidos());
        }

        if (dto.particularidadeContrato() != null) {
            contrato.setParticularidadeContrato(dto.particularidadeContrato());
        }

        if (dto.slaAtendimentoContratado() != null) {
            contrato.setSlaAtendimentoContratado(dto.slaAtendimentoContratado());
        }

        if (dto.preventiva() != null) {
            contrato.setPreventiva(dto.preventiva());
        }

        if (dto.corretiva() != null) {
            contrato.setCorretiva(dto.corretiva());
        }

        if (dto.laboratorio() != null) {
            contrato.setLaboratorio(dto.laboratorio());
        }

        if (dto.despesaViagemFrete() != null) {
            contrato.setDespesaViagemFrete(dto.despesaViagemFrete());
        }

        if (dto.pecas() != null) {
            contrato.setPecas(dto.pecas());
        }

        if (dto.contato() != null) {
            contrato.setContato(dto.contato());
        }

        if (dto.telefone() != null) {
            contrato.setTelefone(dto.telefone());
        }

        if (dto.email() != null) {
            contrato.setEmail(dto.email());
        }

        if (dto.endereco() != null) {
            contrato.setEndereco(dto.endereco());
        }

        if (dto.executivoResponsavelDemandasComerciais() != null) {
            contrato.setExecutivoResponsavelDemandasComerciais(
                    dto.executivoResponsavelDemandasComerciais());
        }

        contratoRepository.save(contrato);

        return new ShowDataContratoDto(contrato);
    }

    public void deleteContrato(UUID id) {
        contratoRepository.delete(findContratoById(id));
    }

    public @Nullable ShowDataContratoDto getContrato(UUID id) {
        return new ShowDataContratoDto(findContratoById(id));
    }
}
