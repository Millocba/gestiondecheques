package com.GestorCheques.crudcheques.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@Document(collection = "cheque")
public class Cheque {

    @Id
    private String id;

    @NotNull(message = "Falta fecha")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaRecepcion;

    @NotBlank(message = "Falta nombre")
    @Size(min = 3, max = 50)
    private String entregadoPor;

    @NotBlank(message="Falta nro de cheque")
    private String numeroCheque;

    @NotBlank(message = "Falta Banco")
    @Size(min = 3, max = 50)
    private String banco;

    @Positive
    private double monto;

    @NotBlank(message = "Falta titular")
    @Size(min = 3, max = 50)
    private String titularCheque;

    @NotBlank(message="Cuit incompleto")
    @Pattern(regexp = "\\d{11}")
    private String cuit;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaCobro;

    @NotBlank(message = "Estado en blanco")
    private String estado;

    private String nombreDestino;

    private String codigoDestino;

    @NotNull
    private Boolean activo = true;

    @AssertTrue(message = "La fecha de cobro debe ser posterior a la fecha de recepción")
    private boolean isFechaCobroValida() {
        return fechaCobro.after(fechaRecepcion);
    }

    @AssertTrue(message = "La fecha de recepción no puede ser menor a 5 años")
    private boolean isFechaRecepcionValida() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaRecepcionLocal = fechaRecepcion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return fechaRecepcionLocal.isAfter(fechaActual.minusYears(5));
    }

}

