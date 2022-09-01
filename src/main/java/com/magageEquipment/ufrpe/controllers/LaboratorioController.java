package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.Laboratorios;
import com.magageEquipment.ufrpe.repositorys.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping
    public List<Laboratorios> getSalaAll(){
        return laboratorioRepository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Laboratorios getSalaAll(@PathVariable UUID uuid){
        Optional<Laboratorios> optionallaboratorio = laboratorioRepository.findById(uuid);
        return optionallaboratorio.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Laboratorios create(@RequestBody Laboratorios laboratorio){
        return laboratorioRepository.save(laboratorio);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID uuid){
        laboratorioRepository.deleteById(uuid);
    }
}
