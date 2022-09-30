package com.gaspar.api.reto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gaspar.api.reto.entity.Paises;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class FichaRequest {

    @NotEmpty
    String nombre;

    @NotEmpty
    String apellidos;

    @NotEmpty
    String telefono;

    @NotEmpty
    String sitioTrabajo;

    @NotEmpty
    String paisResidencia;

    @NotEmpty
    String ciudadResidencia;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaNacimiento;

}
