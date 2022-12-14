package com.gaspar.api.reto.controller;

import com.gaspar.api.reto.dto.FichaRequest;
import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.entity.Paises;
import com.gaspar.api.reto.service.FichaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ficha")
public class FichaController {

    final FichaService service;

    public FichaController(FichaService service) {
        this.service = service;
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar nueva ficha",responses = {
            @ApiResponse(responseCode = "202",description = "Creado")
    })
    ResponseEntity<FichaPersonal> guardarFicha(@Valid @RequestBody FichaRequest fichaRequest,
                                               BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        FichaPersonal fichaPersonal1 = service.save(fichaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaPersonal1);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ficha",responses = {
            @ApiResponse(responseCode = "404",description = "Ficha no encontrada"),
            @ApiResponse(responseCode = "200",description = "Ficha Actualizada")
    })
    ResponseEntity<FichaPersonal> actualizarFicha(@PathVariable Integer id,
                                                  @Valid @RequestBody FichaRequest fichaRequest,
                                                  BindingResult result)
    {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        FichaPersonal update = service.update(id, fichaRequest);
        if(update==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }

    @GetMapping("/todos")
    @Operation(summary = "Obtener listado de fichas ingresada, puede filtrar por pais",responses = {
            @ApiResponse(responseCode = "404",description = "No hay fichas ingresadas"),
            @ApiResponse(responseCode = "200",description = "Fichas obtenidas")
    })
    ResponseEntity<List<FichaPersonal>> traerDatos(@RequestParam(value = "pais",required = false)String pais){
        List<FichaPersonal> fichaPersonals;
        if(pais!=null){
            fichaPersonals = service.getByCountry(pais);
        }else{
            fichaPersonals = service.getAll();
        }
        if(fichaPersonals.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fichaPersonals);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ficha mediante Id",responses = {
            @ApiResponse(responseCode = "404",description = "Ficha no encontrada"),
            @ApiResponse(responseCode = "200",description = "Ficha obtenida")
    })
    ResponseEntity<FichaPersonal> obtenerFicha(@PathVariable Integer id){
        FichaPersonal ficha = service.getFicha(id);
        if(ficha==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ficha);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ficha usando Id",responses = {
            @ApiResponse(responseCode = "404",description = "Ficha no encontrada"),
            @ApiResponse(responseCode = "200",description = "Ficha Eliminada")
    })
    ResponseEntity<FichaPersonal> eliminarFicha(@PathVariable Integer id){
        FichaPersonal ficha = service.getFicha(id);
        if(ficha==null){
            return ResponseEntity.notFound().build();
        }
        service.delete(ficha);
        return ResponseEntity.ok(ficha);
    }

    String formatMessage(BindingResult result){
        String collect = result.getFieldErrors().stream().map(err -> {
            return err.getField() + "->" + err.getDefaultMessage();
        }).collect(Collectors.joining(","));
        return collect;
    }
}
