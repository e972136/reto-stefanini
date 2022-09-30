package com.gaspar.api.reto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="reparaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"IdReparacion","descripcion","cantidad","valorUnitario","creadoEn"})
public class Reparacion {
    @Id
    Integer IdReparacion;
    String descripcion;
    Integer cantidad;
    BigDecimal valorUnitario;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate creadoEn;
}
