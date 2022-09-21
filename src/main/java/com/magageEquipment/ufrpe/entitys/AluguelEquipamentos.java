package com.magageEquipment.ufrpe.entitys;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AluguelEquipamentos implements Serializable  {

    private static final Long serialVersionUID = 2L;

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)")
    private  UUID id;

    private  String name;

    private String email;

    private LocalDateTime solicitacao;

    private LocalDateTime devolucao;

    private Long tempoDeUso;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private Equipamentos equipamento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AluguelEquipamentos)) return false;
        AluguelEquipamentos resquestEquipament = (AluguelEquipamentos) o;
        var intervaloDeUso = getDevolucao().getHour()*60 + getDevolucao().getMinute() > resquestEquipament.getSolicitacao().getHour()*60 +resquestEquipament.getSolicitacao().getMinute()
                            && (resquestEquipament.getSolicitacao().getHour()*60 +resquestEquipament.getSolicitacao().getMinute() >= getSolicitacao().getHour()*60+ getSolicitacao().getMinute()
                            || resquestEquipament.getDevolucao().getHour()*60 +resquestEquipament.getDevolucao().getMinute()>getSolicitacao().getHour()*60+getSolicitacao().getMinute());

        return Objects
                .equals(getSolicitacao().toLocalDate(), resquestEquipament.getSolicitacao().toLocalDate())
                    && Objects.equals(getEquipamento().getId(),resquestEquipament.getEquipamento().getId())
                    &&intervaloDeUso ;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getSolicitacao(), getDevolucao(), getTempoDeUso());
    }


}