package com.GestorCheques.crudcheques.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.GestorCheques.crudcheques.models.Cheque;

@Repository
public interface ChequeRepository extends MongoRepository<Cheque, String> {
    List<Cheque> findByActivoTrue();

    List<Cheque> findByMontoGreaterThan(double monto);

    List<Cheque> findByFechaRecepcionBetween(Date fecha1, Date fecha2);
}





