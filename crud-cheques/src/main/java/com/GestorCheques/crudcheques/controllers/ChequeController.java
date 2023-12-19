package com.GestorCheques.crudcheques.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarCheque(@PathVariable String id, @RequestBody Cheque cheque) {
        Optional<Cheque> chequeExistente = chequeService.detalleCheque(id);

        if (chequeExistente.isPresent()) {
            Cheque chequeGuardado = cheque;
            // Guarda el cheque actualizado en la base de datos
            Cheque chequeModificado = chequeService.guardarCheque(chequeGuardado);
            
            return ResponseEntity.ok("Cheque modificado con ID: " + chequeModificado.getId());
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra el cheque, devuelve 404 Not Found
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cheque>> listarCheques() {
        List<Cheque> cheques = chequeService.listarCheques();

        if (cheques.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay cheques, devuelve 204 No Content
        } else {
            return ResponseEntity.ok(cheques); // Si hay cheques, devuelve la lista de cheques
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Cheque> detalleCheque(@PathVariable String id) {
        Optional<Cheque> cheque = chequeService.detalleCheque(id);
        
        if (cheque.isPresent()) {
            return ResponseEntity.ok(cheque.get()); // Si se encuentra el cheque, devuelve sus detalles
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra el cheque, devuelve 404 Not Found
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCheque(@PathVariable String id) {
        Optional<Cheque> chequeExistente = chequeService.detalleCheque(id);

        if (chequeExistente.isPresent()) {
            Cheque cheque = chequeExistente.get();
            cheque.setActivo(false); // Marca el cheque como inactivo
            
            chequeService.guardarCheque(cheque); // Guarda el cheque actualizado en la base de datos
            
            return ResponseEntity.ok("Cheque eliminado lógicamente con ID: " + id);
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra el cheque, devuelve 404 Not Found
        }
    }
}

