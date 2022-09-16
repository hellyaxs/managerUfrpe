package com.magageEquipment.ufrpe.DTOs;

import com.magageEquipment.ufrpe.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoriosDTO {


    private UUID id;

    @NotNull
    private Integer identificacao;

    @Size(max = 150)
    @NotNull(message = "a localização não poder ser nula")
    private String localizacao;

    @NotNull
    @PositiveOrZero
    private Integer capacidade;

    @Size(max = 500)
    private String descricao;

    private Status disponibilidade;
}
