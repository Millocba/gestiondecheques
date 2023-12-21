package com.GestorCheques.crudcheques.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "cheque")
public class Cheque {

    @Id
    private String id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaRecepcion;

    @NotBlank
    @Size(min = 3, max = 50)
    private String entregadoPor;

    @NotBlank
    private String numeroCheque;

    @NotBlank
    @Size(min = 3, max = 50)
    private String banco;

    @Positive
    private double monto;

    @NotBlank
    @Size(min = 3, max = 50)
    private String titularCheque;

    @Pattern(regexp = "\\d{11}")
    private String cuit;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaCobro;

    @NotBlank
    private String estado;

    private String nombreDestino;

    private String codigoDestino;

    @NotNull
    private Boolean activo = true;
}

