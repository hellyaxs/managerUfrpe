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


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "equipament")
public class Equipamentos implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private String identificacao;

    private String marca;

    private String modelo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer codigo;

    @Enumerated(value = EnumType.STRING)
    private Status disponibilidade;

}