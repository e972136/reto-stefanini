package com.gaspar.api.reto.controller;

import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.service.FichaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FichaController {

    final FichaService service;

    public FichaController(FichaService service) {
        this.service = service;
    }

    @PostMapping("/guardar")
    ResponseEntity<FichaPersonal> guardarFicha(@RequestBody FichaPersonal fichaPersonal){
        FichaPersonal fichaPersonal1 = service.guardarNuevo(fichaPersonal);
        return ResponseEntity.ok(fichaPersonal1);
    }

    @GetMapping("/todos")
    ResponseEntity<List<FichaPersonal>> traerTodos(){
        List<FichaPersonal> fichaPersonals = service.taerTodos();
        return ResponseEntity.ok(fichaPersonals);
    }


}
