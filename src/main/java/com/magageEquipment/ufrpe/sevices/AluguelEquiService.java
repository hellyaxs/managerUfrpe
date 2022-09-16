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

import java.util.List;

@Service
public class AluguelEquiService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private EquipamentosRepository equipamentosRepository;

    @Transactional
    public ResponseEntity<String> alugar(AluguelEquipamentos aluguelEquipamentos){
        var equipamento = equipamentosRepository.getReferenceById(aluguelEquipamentos.getEquipamento().getId());
        if (equipamento.getDisponibilidade() == Status.DISPONIVEL){
            equipamento.setDisponibilidade(Status.INDISPONIVEL);
            aluguelEquipamentos.setEquipamento(equipamento);
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
