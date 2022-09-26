package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.Equipamentos;
import com.magageEquipment.ufrpe.repositorys.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    @Autowired
    private EquipamentosRepository equipamentosRepository;

    @GetMapping
    public List<Equipamentos> getEquipamentoAll(){
        return equipamentosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipamentos getEquipamentoById(@PathVariable Long id){
        Optional<Equipamentos> optionalEquipamentos = equipamentosRepository.findById(id);
        return optionalEquipamentos.orElse(null);
    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @Transactional
    public Equipamentos createEquipamento(@RequestBody Equipamentos equipamentos){
        return equipamentosRepository.save(equipamentos);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void deleteEquipamentoById(@PathVariable Long id){
        equipamentosRepository.deleteById(id);

    }
}
