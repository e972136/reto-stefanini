package com.gaspar.api.reto.repository;

import com.gaspar.api.reto.entity.FichaPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaRepository extends JpaRepository<FichaPersonal,Integer> {
}
