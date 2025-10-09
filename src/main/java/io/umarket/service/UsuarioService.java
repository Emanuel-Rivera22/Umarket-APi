package io.umarket.service;

import io.umarket.model.Usuario;
import io.umarket.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesario para la encriptación

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    // Campo para la inyección del BCryptPasswordEncoder
    private final PasswordEncoder passwordEncoder; 

    // Constructor modificado para inyectar PasswordEncoder y UsuarioRepository
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder; 
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findByUserName(nombre);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Guarda el usuario en la base de datos después de encriptar su contraseña.
     */
    public Usuario guardarUsuario(Usuario usuario) {
        // 1. Encriptar la contraseña antes de guardarla.
        String rawPassword = usuario.getUserPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword); 
        
        // 2. Sobreescribir la contraseña en texto plano con la encriptada.
        usuario.setUserPassword(encodedPassword); 

        // 3. Guardar el objeto Usuario modificado.
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }
}