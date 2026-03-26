package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "contrato")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String codErp;

    private String cnpj;

    private String numeroContratoErp;

    private String empresa;

    private String cidade;

    private String estado;

    @Enumerated(EnumType.STRING)
    private SituacaoContrato situacao;

    private String baseAtendimento;

    private String equipeAtendimento;

    private String equipamentosAtendidos;

    private String particularidadeContrato;

    private String slaAtendimentoContratado;

    @Enumerated(EnumType.STRING)
    private PreventivaContrato preventiva;

    private String corretiva;

    private String laboratorio;

    private String despesaViagemFrete;

    private String pecas;

    private String contato;

    private String telefone;

    @Email
    private String email;

    private String endereco;

    private String executivoResponsavelDemandasComerciais;

}
