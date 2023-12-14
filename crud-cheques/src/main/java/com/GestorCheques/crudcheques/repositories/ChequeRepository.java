package com.GestorCheques.crudcheques.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.GestorCheques.crudcheques.models.Cheque;

@Repository
public interface ChequeRepository extends MongoRepository<Cheque, String> {
    // Aquí puedes agregar métodos adicionales si es necesario
}

