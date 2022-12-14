package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.entitys.Equipamentos;
import com.magageEquipment.ufrpe.sevices.AluguelEquiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/AlugarEquipamentos")
public class AluguelEquiController {


    @Autowired
    private AluguelEquiService aluguelEquiService;

    @PostMapping
    public ResponseEntity<String> novoAlugel(@RequestBody AluguelEquipamentos aluguelEquipamentos){
       return aluguelEquiService.alugar(aluguelEquipamentos);
    }

    @GetMapping("/{id}")
    public List<AluguelEquipamentos> getAll(@PathVariable("id") Long uuid){
        return aluguelEquiService.findByequipamento(uuid);
    }
}
