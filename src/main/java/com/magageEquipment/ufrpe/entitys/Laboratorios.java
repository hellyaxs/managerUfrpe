package com.magageEquipment.ufrpe.entitys;

import com.magageEquipment.ufrpe.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "laboratorios")
public class Laboratorios implements Serializable {

    private static final Long serialVersionUId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String identificacao;

    private String localizacao;

    private Integer capacidade;

    private String descricao;


    private Boolean disponibilidade;


}