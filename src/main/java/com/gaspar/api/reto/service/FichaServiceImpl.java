package com.gaspar.api.reto.service;

import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.repository.FichaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaServiceImpl implements FichaService{

    final FichaRepository repository;

    public FichaServiceImpl(FichaRepository repository) {
        this.repository = repository;
    }

    @Override
    public FichaPersonal guardarNuevo(FichaPersonal ficha) {
        return repository.save(ficha);
    }

    @Override
    public FichaPersonal actualizar(FichaPersonal ficha) {
        return null;
    }

    @Override
    public FichaPersonal eliminar(int id) {
        return null;
    }

    @Override
    public List<FichaPersonal> taerTodos() {
        return repository.findAll();
    }
}
