package com.GestorCheques.crudcheques.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.GestorCheques.crudcheques.controllers.ChequeNotFoundException;
import com.GestorCheques.crudcheques.models.Cheque;
import com.GestorCheques.crudcheques.repositories.ChequeRepository;

@Service
public class ChequeService {

    private final ChequeRepository chequeRepository;

    public ChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    public Cheque guardarCheque(Cheque cheque) {
        return chequeRepository.save(cheque);
    }

    public List<Cheque> listarCheques() {
        return chequeRepository.findAll();
    }
   
    public List<Cheque> listarChequesActivos() {
        return chequeRepository.findByActivoTrue();
    }



    //sin uso
    public Optional<Cheque> detalleCheque(String id) {
        return chequeRepository.findById(id);
    }

    public void eliminarChequeLogico(String id) {
        Optional<Cheque> chequeExistente = detalleCheque(id);

        if (chequeExistente.isPresent()) {
            // Guarda el cheque actualizado en la base de datos
            Cheque cheque = chequeExistente.get();
            cheque.setActivo(false); // Marca el cheque como inactivo
            chequeRepository.save(cheque);
        } else {
            throw new ChequeNotFoundException(id);
        }
    }

    public Cheque buscarChequePorId(String id) {
        return chequeRepository.findById(id)
                .orElseThrow(() -> new ChequeNotFoundException(id));
    }
    

    public Cheque modificarCheque(String id, Cheque cheque) {
        Optional<Cheque> chequeExistente = detalleCheque(id);

        if (chequeExistente.isPresent()) {
            // Guarda el cheque actualizado en la base de datos
            return guardarCheque(cheque);
        } else {
            throw new ChequeNotFoundException(id);
        }
        
    }

    public List<Cheque> buscarPorMonto(double monto) {
        return chequeRepository.findByMontoGreaterThan(monto);
    }

    public List<Cheque> buscarPorFecha(Date fecha1, Date fecha2) {
        return chequeRepository.findByFechaRecepcionBetween(fecha1, fecha2);
    }

}

