package com.magageEquipment.ufrpe.entitys;

import com.magageEquipment.ufrpe.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "equipament")
public class Equipamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String marca;

    private String modelo;

    @Enumerated
    private Status disponibilidade;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}