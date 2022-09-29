package com.gaspar.api.reto.controller;

import com.gaspar.api.reto.dto.FichaRequest;
import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.service.FichaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FichaController {

    final FichaService service;

    public FichaController(FichaService service) {
        this.service = service;
    }

    @PostMapping("/guardar")
    ResponseEntity<FichaPersonal> guardarFicha(@Valid @RequestBody FichaRequest fichaRequest, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        FichaPersonal fichaPersonal1 = service.save(fichaRequest);
        return ResponseEntity.ok(fichaPersonal1);
    }

    @PutMapping("/{id}")
    ResponseEntity<FichaPersonal> actualizarFicha(@PathVariable Integer id,
                                                  @Valid @RequestBody FichaRequest fichaRequest,
                                                  BindingResult result)
    {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        return ResponseEntity.ok(service.update(id,fichaRequest));
    }

    @GetMapping("/todos")
    ResponseEntity<List<FichaPersonal>> traerTodos(){
        List<FichaPersonal> fichaPersonals = service.getAll();
        return ResponseEntity.ok(fichaPersonals);
    }

    @GetMapping("/{id}")
    ResponseEntity<FichaPersonal> obtenerFicha(@PathVariable Integer id){
        return ResponseEntity.ok(service.getFicha(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<FichaPersonal> eliminarFicha(@PathVariable Integer id){
        return ResponseEntity.ok(service.delete(id));
    }

    String formatMessage(BindingResult result){
        String collect = result.getFieldErrors().stream().map(err -> {
            return err.getField() + "->" + err.getDefaultMessage();
        }).collect(Collectors.joining(","));
        return collect;
    }
}
