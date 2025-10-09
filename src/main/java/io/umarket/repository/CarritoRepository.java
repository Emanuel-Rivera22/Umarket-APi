package io.umarket.repository;

import io.umarket.model.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends MongoRepository<Carrito, String> {
    Optional<Carrito> findByUsuarioId(String usuarioId);
}
