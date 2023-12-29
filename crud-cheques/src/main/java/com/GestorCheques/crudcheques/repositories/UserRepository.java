package com.GestorCheques.crudcheques.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.GestorCheques.crudcheques.models.User;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
