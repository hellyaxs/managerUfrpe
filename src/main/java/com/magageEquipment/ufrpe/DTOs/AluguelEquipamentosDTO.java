package com.magageEquipment.ufrpe.DTOs;

import com.magageEquipment.ufrpe.entitys.Equipamentos;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class AluguelEquipamentosDTO {

    private UUID id;

    private  String name;

    private String email;

    private LocalDateTime solicitacao;

    private LocalDateTime devolucao;

    private Long tempoDeUso;

    private UUID equipamento;

}
