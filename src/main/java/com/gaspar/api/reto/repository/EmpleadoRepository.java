package com.gaspar.api.reto.repository;


import com.gaspar.api.reto.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
}
