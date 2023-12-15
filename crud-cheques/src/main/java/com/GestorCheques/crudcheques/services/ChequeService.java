package com.GestorCheques.crudcheques.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Optional<Cheque> detalleCheque(String id) {
        return chequeRepository.findById(id);
    }

}

