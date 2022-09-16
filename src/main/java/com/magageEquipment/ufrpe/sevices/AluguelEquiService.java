package com.magageEquipment.ufrpe.sevices;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.enums.Status;
import com.magageEquipment.ufrpe.repositorys.AluguelRepository;
import com.magageEquipment.ufrpe.repositorys.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void alugar(AluguelEquipamentos aluguelEquipamentos){
        var equipamento = equipamentosRepository.getReferenceById(aluguelEquipamentos.getEquipamento().getId());
        if (equipamento.getDisponibilidade() == Status.DISPONIVEL){
            aluguelRepository.save(aluguelEquipamentos);
            equipamento.setDisponibilidade(Status.INDISPONIVEL);
            equipamentosRepository.save(equipamento);
        }else{
            System.out.println("não disponivel");
        }
    }

    public List<AluguelEquipamentos> findAll(){
        return aluguelRepository.findAll();
    }
}
