package com.magageEquipment.ufrpe.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "aluguel_laboraorios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelLaboraorios implements Serializable {

    public static final Long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime solicitacao;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime devolucao;

    private Long tempoDeUso;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private Laboratorios laboratorios;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AluguelLaboraorios)) return false;
        AluguelLaboraorios requestLaboratorio = (AluguelLaboraorios) o;

        var intervaloDeUso = getDevolucao().getHour()*60 + getDevolucao().getMinute() > requestLaboratorio.getSolicitacao().getHour()*60 +requestLaboratorio.getSolicitacao().getMinute()
                && (requestLaboratorio.getSolicitacao().getHour()*60 +requestLaboratorio.getSolicitacao().getMinute() >= getSolicitacao().getHour()*60+ getSolicitacao().getMinute()
                || requestLaboratorio.getDevolucao().getHour()*60 +requestLaboratorio.getDevolucao().getMinute()>getSolicitacao().getHour()*60+getSolicitacao().getMinute());

        return Objects
                .equals(getSolicitacao().toLocalDate(), requestLaboratorio.getSolicitacao().toLocalDate())
                && Objects.equals(getLaboratorios().getId(),requestLaboratorio.getLaboratorios().getId())
                &&intervaloDeUso ;
    }

    @Override
    public int hashCode() {
        int result = getSolicitacao() != null ? getSolicitacao().hashCode() : 0;
        result = 31 * result + (getDevolucao() != null ? getDevolucao().hashCode() : 0);
        result = 31 * result + (getLaboratorios() != null ? getLaboratorios().hashCode() : 0);
        return result;
    }
}