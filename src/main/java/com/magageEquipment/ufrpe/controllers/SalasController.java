package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.Salas;
import com.magageEquipment.ufrpe.repositorys.SalasRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sala")
public class SalasController {

    @Autowired
    private SalasRepository salasRepository;

    @GetMapping
    public List<Salas> getSalaAll(){
        return salasRepository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Salas getSalaAll(@PathVariable UUID uuid){
        Optional<Salas> optionalSalas = salasRepository.findById(uuid);
        return optionalSalas.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Salas create(@RequestBody Salas salas){
        return salasRepository.save(salas);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID uuid){
        salasRepository.deleteById(uuid);
    }
}
