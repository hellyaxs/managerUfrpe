package com.magageEquipment.ufrpe.sevices;

import com.magageEquipment.ufrpe.entitys.AluguelLaboraorios;
import com.magageEquipment.ufrpe.enums.Status;
import com.magageEquipment.ufrpe.repositorys.AluguelLaboraoriosRepository;
import com.magageEquipment.ufrpe.repositorys.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AluguelLabService {

    @Autowired
    private AluguelLaboraoriosRepository aluguelLaboraoriosRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Transactional
    public ResponseEntity<String> alugar(AluguelLaboraorios aluguelLaboraorios){
        var laboratorio = laboratorioRepository.findById(aluguelLaboraorios.getLaboratorios().getId());

        if(laboratorio.get().getDisponibilidade() == Status.DISPONIVEL){
            aluguelLaboraoriosRepository.save(aluguelLaboraorios);
            laboratorio.get().setDisponibilidade(Status.INDISPONIVEL);
            laboratorioRepository.save(laboratorio.get());
            return new ResponseEntity<>("solicitação acteita",HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("o equipamento esta indisponivel",HttpStatus.CONFLICT);
        }


    }
}
