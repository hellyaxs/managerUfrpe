package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.sevices.AluguelEquiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AlugarEquipamentos")
public class AluguelEquiController {


    @Autowired
    private AluguelEquiService aluguelEquiService;

    @PostMapping
    public void novoAlugel(@RequestBody AluguelEquipamentos aluguelEquipamentos){
        aluguelEquiService.alugar(aluguelEquipamentos);
    }

    @GetMapping
    public List<AluguelEquipamentos> getAll(){
        return aluguelEquiService.findAll();
    }
}
