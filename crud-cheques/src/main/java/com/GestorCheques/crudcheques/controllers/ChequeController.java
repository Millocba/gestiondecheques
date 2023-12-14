package com.GestorCheques.crudcheques.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestorCheques.crudcheques.models.Cheque;
import com.GestorCheques.crudcheques.services.ChequeService;

@RestController
@RequestMapping("/cheques")
public class ChequeController {

    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarCheque(@RequestBody Cheque cheque) {
        Cheque chequeGuardado = chequeService.guardarCheque(cheque);
        return ResponseEntity.ok("Cheque agregado con ID: " + chequeGuardado.getId());
    }
}

