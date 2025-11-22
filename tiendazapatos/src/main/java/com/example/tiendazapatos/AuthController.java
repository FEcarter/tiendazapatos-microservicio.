package com.example.tiendazapatos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 1. Define que esta clase es un controlador que responde a peticiones web
@RestController
// 2. Hace que todas las rutas de esta clase empiecen con /api/auth
@RequestMapping("/api/auth")
public class AuthController {

    // 3. Una "base de datos" falsa y simple para guardar usuarios en memoria.
    // La clave es el nombre de usuario (String), el valor es la contraseña (String).
    private static final Map<String, String> users = new ConcurrentHashMap<>();

    // 4. Define el endpoint para el registro (POST /api/auth/register)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserCredentials request) {
        // Comprueba si el usuario ya existe
        if (users.containsKey(request.username())) {
            // Si existe, devuelve un error 409 (Conflicto)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya existe.");
        }
        // Si no existe, lo guarda en nuestra base de datos falsa
        users.put(request.username(), request.password());
        // Devuelve una respuesta 201 (Creado) con un mensaje de éxito
        return ResponseEntity.status(HttpStatus.CREATED).body("¡Usuario registrado con éxito!");
    }

    // 5. Define el endpoint para el login (POST /api/auth/login)
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserCredentials request) {
        // Busca al usuario en la base de datos falsa
        String storedPassword = users.get(request.username());

        // Comprueba si el usuario existe y si la contraseña coincide
        if (storedPassword != null && storedPassword.equals(request.password())) {
            // Si coincide, devuelve un 200 (OK) con un mensaje de éxito
            return ResponseEntity.ok("¡Inicio de sesión exitoso!");
        } else {
            // Si no coincide, devuelve un error 401 (No autorizado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos.");
        }
    }
}

// 6. Un modelo simple para recibir los datos de usuario (username y password) en las peticiones
record UserCredentials(String username, String password) {}
