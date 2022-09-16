package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.Laboratorios;
import com.magageEquipment.ufrpe.repositorys.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/laboratorios")
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
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Laboratorios create(@RequestBody Laboratorios laboratorio){
        return laboratorioRepository.save(laboratorio);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void delete(@PathVariable UUID uuid){
        laboratorioRepository.deleteById(uuid);
    }
}
