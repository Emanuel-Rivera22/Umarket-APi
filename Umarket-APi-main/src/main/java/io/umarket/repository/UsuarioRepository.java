package io.umarket.repository;

import io.umarket.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByUserName(String userName);
    Optional<Usuario> findByEmail(String email);
}
