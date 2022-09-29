package com.gaspar.api.reto.repository;

import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.entity.Paises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<FichaPersonal,Integer> {
    List<FichaPersonal> findBypaisResidencia(Paises paises);
}
