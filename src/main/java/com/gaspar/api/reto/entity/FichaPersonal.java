package com.gaspar.api.reto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="ficha_personal")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
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

    @Transient
    public long getEdad(){
        long between = ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
        return between;
    }
}
