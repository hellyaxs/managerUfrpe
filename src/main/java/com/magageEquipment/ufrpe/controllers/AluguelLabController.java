package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.AluguelLaboraorios;
import com.magageEquipment.ufrpe.sevices.AluguelLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugarLaboratorios")
public class AluguelLabController {

    @Autowired
    private AluguelLabService aluguelLabService;

    @PostMapping
    public ResponseEntity<String> alugar(@RequestBody AluguelLaboraorios aluguelLaboraorios){
       return aluguelLabService.alugar(aluguelLaboraorios);
    }

    @GetMapping("/{id}")
    public List<AluguelLaboraorios> getAll(@PathVariable Long id){
        return aluguelLabService.findByEquipament(id);
    }
}
