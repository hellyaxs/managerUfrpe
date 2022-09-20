package com.magageEquipment.ufrpe.sevices;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.enums.Status;
import com.magageEquipment.ufrpe.repositorys.AluguelRepository;
import com.magageEquipment.ufrpe.repositorys.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AluguelEquiService {

    private static final Long TIME_DEVOLUCTION= 2L;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private EquipamentosRepository equipamentosRepository;

    @Transactional
    public ResponseEntity<String> alugar(AluguelEquipamentos aluguelEquipamentos){
        var equipamento = equipamentosRepository.getReferenceById(aluguelEquipamentos.getEquipamento().getId());
        if (equipamento.getDisponibilidade() == Status.DISPONIVEL){
            aluguelEquipamentos.setEquipamento(equipamento);
            var dataDevolucao = aluguelEquipamentos.getSolicitacao().plusHours(aluguelEquipamentos.getTempoDeUso());
            aluguelEquipamentos.setDevolucao(dataDevolucao);

            var alugueis = aluguelRepository.findBySolicitation(aluguelEquipamentos.getSolicitacao());
            for (var aluguelEquipamentos1: alugueis ) {
              var verifica = aluguelEquipamentos1.equals(aluguelEquipamentos);
              if (verifica){
                  return new ResponseEntity<>("o equipamento nao esta indisponivel nesta data e horario",HttpStatus.CONFLICT);
              }
            }

            aluguelRepository.save(aluguelEquipamentos);
            return new ResponseEntity<>("solicitacao aceita", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("o equipamento nao esta indisponivel",HttpStatus.CONFLICT);
        }
    }

    public List<AluguelEquipamentos> findAll(){
        return aluguelRepository.findAll();
    }
}
