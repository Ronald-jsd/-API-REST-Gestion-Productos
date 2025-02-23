// ProductService.java
package edu.galaxy.apirest.services;

import edu.galaxy.apirest.entities.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    // Lista todos los productos sin ningún filtro
    List<Product> listarTodos();

    // Lista solo los productos que están activos
    List<Product> mostrarSoloActivos();

    // Busca un producto por su ID, asegurando que esté activo
    Optional<Product> findById(Long id);

    // Busca productos por nombre y estado (activo o inactivo)
    List<Product> findByNameAndState(String name, Boolean state);

    // Busca productos que contengan una coincidencia parcial en el nombre
    List<Product> findByNameLike(String name);

    // Guarda un nuevo producto o actualiza uno existente, asignando su estado
    Product save(Product product, Boolean state);

    // Elimina un producto físicamente de la base de datos por ID
    void delete(Long id);

    // Desactiva un producto (eliminado lógico) por su ID
    void deactivateProduct(@PathVariable Long id);
}