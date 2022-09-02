package com.magageEquipment.ufrpe.entitys;

import com.magageEquipment.ufrpe.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "equipament")
public class Equipamentos implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String identificacao;

    private String marca;

    private String modelo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer codigo;

}