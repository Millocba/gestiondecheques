package com.GestorCheques.crudcheques.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "cheque")
public class Cheque {

    @Id
    private String id;
    private Date fechaRecepcion;
    private String entregadoPor;
    private String numeroCheque;
    private String banco;
    private double monto;
    private String titularCheque;
    private String cuit;
    private Date fechaCobro;
    private String estado;
    private String nombreDestino;
    private String codigoDestino;
    private Boolean activo = true;
    
}

