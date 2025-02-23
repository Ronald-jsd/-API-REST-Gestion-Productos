// ProductServiceImpl.java
package edu.galaxy.apirest.services;

import edu.galaxy.apirest.entities.Product;
import edu.galaxy.apirest.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // Constructor que inyecta el repositorio de productos
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Devuelve la lista completa de productos sin aplicar filtros
    @Override
    public List<Product> listarTodos() {
        return productRepository.findAll();
    }

    // Devuelve únicamente los productos que están activos
    @Override
    public List<Product> mostrarSoloActivos() {
        return productRepository.mostrarSoloActivos();
    }

    // Busca un producto por su ID y verifica que esté activo
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findByStateAndId(true, id);
    }

    // Filtra los productos por nombre y estado (activo/inactivo)
    @Override
    public List<Product> findByNameAndState(String name, Boolean state) {
        return productRepository.findByNameAndState(name, state);
    }

    // Busca productos que contengan coincidencias en su nombre
    @Override
    public List<Product> findByNameLike(String name) {
        return productRepository.findByNameLike(name);
    }

    // Guarda un producto nuevo o actualiza el existente y establece su estado (activo/inactivo)
    @Override
    public Product save(Product product, Boolean state) {
        product.setState(state);
        return productRepository.save(product);
    }

    // Elimina físicamente por su ID
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // Realiza una eliminación lógica cambiando el estado del producto a inactivo
    @Override
    public void deactivateProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            this.save(product, false); // Cambiar el estado a inactivo
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
    }
}