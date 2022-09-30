package com.gaspar.api.reto.service;


import com.gaspar.api.reto.entity.Empleado;
import com.gaspar.api.reto.helper.CSVHelper;
import com.gaspar.api.reto.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    final EmpleadoRepository repository;

    public CSVService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public void save(MultipartFile file) {
        int i=0;
        try {
            int batchSize = 1000;
            List<Empleado> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<Empleado> subiendo = tutorials.subList(i,totalElementos);
                    repository.saveAll(subiendo);
                    System.out.println("Saved "+i+" "+(i+batchSize));
                    break;
                }
                List<Empleado> subiendo = tutorials.subList(i,i+batchSize);
                repository.saveAll(subiendo);
                System.out.println("Saved "+i+" "+(i+batchSize));
            }

        } catch (IOException e) {
            System.out.println(i);

            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Empleado> getAllTutorials() {
        return repository.findAll();
    }
}
