package com.gaspar.api.reto.service;

import com.gaspar.api.reto.dto.FichaRequest;
import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.entity.Paises;

import java.util.List;

public interface FichaService {
    FichaPersonal save(FichaRequest ficha);
    FichaPersonal update(Integer id, FichaRequest ficha);
    void delete(FichaPersonal ficha);
    FichaPersonal getFicha(int id);
    List<FichaPersonal> getAll();
    List<FichaPersonal> getByCountry(Paises paises);
    Paises obtenerPais(String paisResidencia);
}
