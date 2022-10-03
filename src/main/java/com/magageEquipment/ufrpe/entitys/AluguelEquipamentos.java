package com.magageEquipment.ufrpe.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String name;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime solicitacao;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime devolucao;

    private Long tempoDeUso;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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