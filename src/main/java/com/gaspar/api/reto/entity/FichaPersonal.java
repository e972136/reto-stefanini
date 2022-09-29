package com.gaspar.api.reto.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name="ficha_personal")
@Data
public class FichaPersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty
    String nombre;

    @NotEmpty
    String apellidos;

    @NotEmpty
    String telefono;

    @NotEmpty
    String sitioTrabajo;


    @Enumerated(EnumType.STRING)
    Paises paisResidencia;

    @NotEmpty
    String ciudadResidencia;


    LocalDate fechaNacimiento;

}
