// ProductRepository.java
package edu.galaxy.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.galaxy.apirest.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Buscar productos por nombre y estado
    List<Product> findByNameAndState(String name, Boolean state);

    // Buscar productos que contengan una parte del nombre y estén activos
    @Query("SELECT p FROM Product p where p.name LIKE %:name% AND p.state = true")
    List<Product> findByNameLike(String name);

    // Listar productos por su estado
    List<Product> findByState(boolean b);

    // Listar solo los productos activos
    @Query("SELECT p FROM Product p WHERE p.state = true")
    List<Product> mostrarSoloActivos();

    // Buscar un producto por ID y estado
    Optional<Product> findByStateAndId(Boolean state, Long id);
}