package com.magageEquipment.ufrpe.sevices;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.entitys.AluguelLaboraorios;
import com.magageEquipment.ufrpe.enums.Status;
import com.magageEquipment.ufrpe.repositorys.AluguelLaboraoriosRepository;
import com.magageEquipment.ufrpe.repositorys.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AluguelLabService {

    @Autowired
    private AluguelLaboraoriosRepository repository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Transactional
    public ResponseEntity<String> alugar(AluguelLaboraorios aluguelLaboraorios){
        var laboratorio = laboratorioRepository.findById(aluguelLaboraorios.getLaboratorios().getId());

        if(laboratorio.get().getDisponibilidade() == Status.DISPONIVEL){
            var dataDevolucao = aluguelLaboraorios.getSolicitacao().plusHours(aluguelLaboraorios.getTempoDeUso());
            aluguelLaboraorios.setDevolucao(dataDevolucao);
            aluguelLaboraorios.setLaboratorios(laboratorio.get());


            var alugueis = findByDateToday(aluguelLaboraorios.getSolicitacao().toLocalDate());
            for (var aluguelEquipamentos1: alugueis ) {
                var verifica = aluguelEquipamentos1.equals(aluguelLaboraorios);
                if (verifica){
                    return new ResponseEntity<>("o Laboratorio nao esta indisponivel nesta data e horario",HttpStatus.CONFLICT);
                }
            }


            repository.save(aluguelLaboraorios);
            return new ResponseEntity<>("solicitacao aceita",HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("o Laboratorio nao esta indisponivel",HttpStatus.CONFLICT);
        }
    }

    public List<AluguelLaboraorios> findAll(){
        return repository.findAll();
    }

    public List<AluguelLaboraorios> findByDateToday(LocalDate today){
        return repository.findAll()
                .stream()
                .filter(aluguelDate-> aluguelDate.getSolicitacao().toLocalDate().equals(today))
                .collect(Collectors.toList());
    }

    public List<AluguelLaboraorios> findByEquipament(Long id){
        return repository.findByLaboratorios_IdOrderByNameAsc(id)
                .stream()
                .filter(lab-> lab.getSolicitacao().toLocalDate().isAfter(LocalDate.now())
                ||lab.getSolicitacao().toLocalDate().equals(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> CancelarAlguel(Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("alguel do laborario cancelado",HttpStatus.ACCEPTED);
    }
}
