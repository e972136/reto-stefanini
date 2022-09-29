package com.gaspar.api.reto.service;

import com.gaspar.api.reto.entity.FichaPersonal;

import java.util.List;

public interface FichaService {
    FichaPersonal guardarNuevo(FichaPersonal ficha);
    FichaPersonal actualizar(FichaPersonal ficha);
    FichaPersonal eliminar(int id);
    List<FichaPersonal> taerTodos();

}
