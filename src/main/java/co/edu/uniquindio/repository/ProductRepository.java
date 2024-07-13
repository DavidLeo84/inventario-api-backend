package co.edu.uniquindio.repository;

import co.edu.uniquindio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    Optional<Product> findByNombre(String nombre);

    Optional<Product> findByCodigo(Integer codigo);
}
