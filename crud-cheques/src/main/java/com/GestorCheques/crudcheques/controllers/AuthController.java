package com.GestorCheques.crudcheques.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestorCheques.crudcheques.auth.AuthResponse;
import com.GestorCheques.crudcheques.auth.LoginRequest;
import com.GestorCheques.crudcheques.auth.RegisterRequest;
import com.GestorCheques.crudcheques.models.User;
import com.GestorCheques.crudcheques.repositories.UserRepository;
import com.GestorCheques.crudcheques.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        //System.out.println("Iniciando sesión...");
        return ResponseEntity.ok(authService.login(request));

    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // Comprueba si ya existe un usuario con el nombre proporcionado
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            // Si el usuario ya existe, devuelve un error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new AuthResponse("El nombre de usuario ya está en uso. Por favor, elige otro.")
            );
        } else {
            // Si el usuario no existe, procede con el registro
            return ResponseEntity.ok(authService.register(request));
        }
    }
    
}   
