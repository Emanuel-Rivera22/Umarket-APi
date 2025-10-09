package io.umarket.service;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public Optional<Carrito> obtenerCarritoPorUsuario(String usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public Carrito crearCarrito(String usuarioId) {
        Carrito carrito = new Carrito(usuarioId);
        return carritoRepository.save(carrito);
    }

    public Carrito agregarProducto(String usuarioId, Producto producto) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElse(new Carrito(usuarioId));
        carrito.agregarProducto(producto);
        return carritoRepository.save(carrito);
    }

    public Carrito eliminarProducto(String usuarioId, Producto producto) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElse(new Carrito(usuarioId));
        carrito.eliminarProducto(producto);
        return carritoRepository.save(carrito);
    }

    // ✅ MÉTODO AGREGADO: Implementación para que VistaController use el ID.
    public Carrito eliminarProductoPorId(String usuarioId, String productoId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElse(new Carrito(usuarioId));

        carrito.eliminarProductoPorId(productoId); // Usa el método del modelo

        return carritoRepository.save(carrito);
    }

    public void eliminarCarrito(String id) {
        carritoRepository.deleteById(id);
    }
}