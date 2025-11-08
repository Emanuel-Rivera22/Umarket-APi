package io.umarket.repository;

import io.umarket.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Usa MongoRepository para bases de datos MongoDB.
// El primer tipo es la Entidad (Pedido), el segundo es el tipo de su ID (@Id)
public interface PedidoRepository extends MongoRepository<Pedido, String> {

    // ✅ MÉTODO CLAVE REQUERIDO: Usado en PedidoService#obtenerOCrearPedido
    // Spring Data genera automáticamente la implementación de esta consulta.
    Optional<Pedido> findByUserId(String userId);

}