package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.Equipamentos;
import com.magageEquipment.ufrpe.repositorys.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {
    @Autowired
    private EquipamentosRepository equipamentosRepository;

    @GetMapping
    public List<Equipamentos> getEquipamentoAll(){
        return equipamentosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipamentos getEquipamentoById(@PathVariable UUID id){
        Optional<Equipamentos> optionalEquipamentos = equipamentosRepository.findById(id);
        return optionalEquipamentos.orElse(null);
    }

    @PostMapping
    public Equipamentos createEquipamento(@RequestBody Equipamentos equipamentos){
        return equipamentosRepository.save(equipamentos);
    }

    @DeleteMapping("{id}")
    public void deleteEquipamentoById(@PathVariable UUID id){
        equipamentosRepository.deleteById(id);

    }
}