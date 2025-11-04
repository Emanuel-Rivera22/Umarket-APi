package io.umarket.repository;

import io.umarket.model.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Usa MongoRepository para bases de datos MongoDB.
// El primer tipo es la Entidad (Carrito), el segundo es el tipo de su ID (@Id)
public interface CarritoRepository extends MongoRepository<Carrito, String> {

    // ✅ MÉTODO CLAVE REQUERIDO: Usado en CarritoService#obtenerOCrearCarrito
    // Spring Data genera automáticamente la implementación de esta consulta.
    Optional<Carrito> findByUserId(String userId);

}