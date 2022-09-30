package com.gaspar.api.reto.controller;

import com.gaspar.api.reto.dto.ResponseMessage;
import com.gaspar.api.reto.entity.Reparacion;
import com.gaspar.api.reto.helper.CSVHelper;
import com.gaspar.api.reto.service.CSVService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin("http://localhost:1001")
@RestController
@RequestMapping("/api/csv")
public class CSVController {
    final CSVService fileService;

    public CSVController(CSVService service) {
        this.fileService = service;
    }

    @PostMapping(path="/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir archivo, seleccionar el archivo ejemplo (Consulta1.scv) cuenta con 11k registros")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                System.out.println(e+"");
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/obtener")
    @Operation(summary = "Obtener informacion almacenada por pagina.")
    public ResponseEntity<Page<Reparacion>> getData(@RequestParam(name="size",required = false, defaultValue = "100") int size,
                                    @RequestParam(name="page",required = false, defaultValue = "0") int page){
         return ResponseEntity.ok(fileService.getAllItem(size, page)) ;
    }

}
