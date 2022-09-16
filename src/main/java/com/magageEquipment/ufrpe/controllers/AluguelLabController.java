package com.magageEquipment.ufrpe.controllers;

import com.magageEquipment.ufrpe.entitys.AluguelLaboraorios;
import com.magageEquipment.ufrpe.sevices.AluguelLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alugarLaboratorios")
public class AluguelLabController {

    @Autowired
    private AluguelLabService aluguelLabService;

    @PostMapping
    public ResponseEntity<String> alugar(@RequestBody AluguelLaboraorios aluguelLaboraorios){
       return aluguelLabService.alugar(aluguelLaboraorios);
    }
}
