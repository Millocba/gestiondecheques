package com.GestorCheques.crudcheques.controllers;

public class ChequeNotFoundException extends RuntimeException {
    public ChequeNotFoundException(String message) {
        super("Cheque no encontrado con ID: " + message);
    }
    // Otros métodos o lógica si es necesaria
}

