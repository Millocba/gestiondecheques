package com.GestorCheques.crudcheques.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestorCheques.crudcheques.models.Cheque;
import com.GestorCheques.crudcheques.models.FechaRequest;
import com.GestorCheques.crudcheques.services.ChequeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cheques")
public class ChequeController {

    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
    
    @PostMapping("/agregar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> agregarCheque(@Valid @RequestBody Cheque cheque) {
        
        Cheque chequeGuardado = chequeService.guardarCheque(cheque);
        return ResponseEntity.ok("Cheque agregado con ID: " + chequeGuardado.getId());
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarCheque(@Valid @PathVariable String id, @RequestBody Cheque cheque) {

        Cheque chequeModificado = chequeService.modificarCheque(id, cheque);
        return ResponseEntity.ok("Cheque modificado con ID: " + chequeModificado.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cheque>> listarCheques() {
        List<Cheque> cheques = chequeService.listarCheques();
        return cheques.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cheques);
    }
    
    @GetMapping("/listaractivos")
    public ResponseEntity<List<Cheque>> listarChequesActivos() {
        List<Cheque> cheques = chequeService.listarChequesActivos();
        return cheques.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cheques);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Cheque> detalleCheque(@PathVariable String id) {
        Cheque cheque = chequeService.buscarChequePorId(id);
        return ResponseEntity.ok(cheque);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCheque(@PathVariable String id) {
        chequeService.eliminarChequeLogico(id);
        return ResponseEntity.ok("Cheque eliminado lógicamente con ID: " + id);
    }

    @GetMapping("/buscarpormonto/{monto}")
    public ResponseEntity<List<Cheque>> buscarChequesPorMontoSuperior(@PathVariable double monto) {
        List<Cheque> cheques = chequeService.buscarPorMonto(monto);
        return cheques.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cheques);
    }

    @GetMapping("/buscarporfecha")
    public ResponseEntity<List<Cheque>> buscarChequesPorFecha(@Valid @RequestBody FechaRequest data) {
        Date fecha1 = data.getFechaInicio();
        Date fecha2 = data.getFechaFin();
        if (fecha2 == null){
            fecha2 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        List<Cheque> cheques = chequeService.buscarPorFecha(fecha1, fecha2);
        return cheques.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cheques);
    }
}
