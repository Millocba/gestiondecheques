package com.GestorCheques.crudcheques.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FechaRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

}

