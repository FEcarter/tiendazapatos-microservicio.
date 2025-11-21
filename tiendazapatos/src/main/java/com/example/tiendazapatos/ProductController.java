package com.example.tiendazapatos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// 1. Define que esta clase responderá a peticiones web
@RestController
// 2. Hace que todas las rutas de esta clase empiecen con /api
@RequestMapping("/api")
public class ProductController {

    // 3. Esta función se ejecutará cuando alguien pida la ruta /products
    @GetMapping("/products")
    public List<Product> getProducts() {
        // 4. Devolvemos una lista de productos de prueba (igual que en Android)
        return List.of(
                new Product(1, "Zapato Deportivo (desde Servidor)", "Ideal para correr.", 89.99, "uri_imagen_1", 10),
                new Product(2, "Zapato Casual (desde Servidor)", "Perfecto para el día a día.", 69.99, "uri_imagen_2", 20),
                new Product(3, "Bota de Cuero (desde Servidor)", "Elegancia y durabilidad.", 129.99, "uri_imagen_3", 15)
        );
    }
}

// 5. Un modelo simple para representar un producto
record Product(int id, String name, String description, double price, String imageUri, int stock) {}
