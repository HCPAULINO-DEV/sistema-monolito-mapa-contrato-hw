package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.controller;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.dto.SaveContratoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.dto.ShowDataContratoDto;
import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.contrato.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<ShowDataContratoDto> created(
            @RequestBody @Valid SaveContratoDto dto,
            UriComponentsBuilder uriBuilder){
        ShowDataContratoDto contrato = contratoService.saveContrato(dto);
        var uri = uriBuilder.path("/contratos/{id}").buildAndExpand(contrato.id()).toUri();

        return ResponseEntity.created(uri).body(contrato);
    }

    @GetMapping
    public ResponseEntity<Page<ShowDataContratoDto>> getContratos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer codErp,
            @RequestParam(required = false) String cnpj,
            @PageableDefault(size = 10) Pageable pageable){
        Page<ShowDataContratoDto> contratos = contratoService.getContratos(nome, codErp, cnpj, pageable);

        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDataContratoDto> getContrato(@PathVariable UUID id){
        return ResponseEntity.ok(contratoService.getContrato(id));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ShowDataContratoDto> patch(
            @PathVariable UUID id,
            @RequestBody @Valid SaveContratoDto dto){

        return ResponseEntity.ok(contratoService.updateContrato(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        contratoService.deleteContrato(id);

        return ResponseEntity.noContent().build();
    }


}
