package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.equipamento.entity;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.entity.Contrato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "equipamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String codigoInterno;

    @Enumerated(EnumType.STRING)
    private TipoEquipamento tipoEquipamento;

    private String fabricante;

    private String descricao;

    private String local;

    private String identificacaoRede;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

}
