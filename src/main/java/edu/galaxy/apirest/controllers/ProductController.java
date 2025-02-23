// Controlador REST para gestionar operaciones relacionadas con los productos
package edu.galaxy.apirest.controllers;

import edu.galaxy.apirest.entities.Product;
import edu.galaxy.apirest.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor // DI automática con Lombok
public class ProductController {

    //Atributo DI
    private final ProductService productService;

    // Obtener todos los productos, activos e inactivos
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.listarTodos();
    }

    // Obtener únicamente los productos que están activos
    @GetMapping("/active")
    public List<Product> getActiveProducts() {
        return productService.mostrarSoloActivos();
    }

    // Buscar un producto activo por su ID usando @PathVariable
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // Buscar un producto activo por su ID usando @RequestParam
    @GetMapping("/getProductByIdRP")
    public Optional<Product> getProductByIdRP(@RequestParam Long id) {
        return productService.findById(id);
    }

    // Buscar productos activos por coincidencia exacta de nombre
    @GetMapping("/search/exact")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.findByNameAndState(name, true);
    }

    // Buscar productos activos que contengan un término parcial en su nombre
    @GetMapping("/search/partial")
    public List<Product> searchByTerm(@RequestParam String characters) {
        return productService.findByNameLike("%" + characters + "%");
    }

    // Crear un nuevo producto (activo por defecto)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product, true);
    }

    // Actualizar completamente un producto existente por su ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existeProduct = productService.findById(id);
        if (existeProduct.isPresent()) {
            product.setId(id); // Asegurar que el ID del producto actualizado sea el mismo
            return productService.save(product, true);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
    }

    // Actualizar parcialmente un producto por su ID
    @PatchMapping("/{id}")
    public Product partialUpdate(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productoExistente = productService.findById(id);
        if (productoExistente.isPresent()) {
            Product actual = productoExistente.get();
            // Actualiza solo los campos que no son nulos en la solicitud
            if (product.getName() != null) actual.setName(product.getName());
            if (product.getDescription() != null) actual.setDescription(product.getDescription());
            if (product.getStock() != null) actual.setStock(product.getStock());
            if (product.getPrecio() != null) actual.setPrecio(product.getPrecio());
            if (product.getImage() != null) actual.setImage(product.getImage());
            return productService.save(actual, true);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
    }

    // Eliminar un producto físicamente por su ID o Hard Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    // Desactivar un producto (eliminación lógica o soft Delete)
    @DeleteMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateProduct(@PathVariable Long id) {
        productService.deactivateProduct(id);
    }
}
