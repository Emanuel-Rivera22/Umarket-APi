package io.umarket.service;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    /**
     * Obtiene el carrito de un usuario existente o crea uno nuevo si no existe.
     */
    public Carrito obtenerOCrearCarrito(String userId) {
        Optional<Carrito> carritoOpt = carritoRepository.findByUserId(userId);
        return carritoOpt.orElseGet(() -> crearNuevoCarrito(userId));
    }

    private Carrito crearNuevoCarrito(String userId) {
        Carrito nuevoCarrito = new Carrito(userId);
        return carritoRepository.save(nuevoCarrito);
    }

    /**
     * Agrega un producto al carrito del usuario.
     */
    public void agregarProducto(String userId, Producto producto) {
        Carrito carrito = obtenerOCrearCarrito(userId);
        carrito.agregarProducto(producto);
        carritoRepository.save(carrito);
    }

    /**
     * Elimina un producto del carrito del usuario.
     */
    public void eliminarProductoPorId(String userId, String productoId) {
        Carrito carrito = obtenerOCrearCarrito(userId);
        carrito.eliminarProductoPorId(productoId);
        carritoRepository.save(carrito);
    }

    /**
     * Obtiene la lista de productos del carrito de un usuario.
     */
    public List<Producto> obtenerProductosDelUsuario(Usuario usuario) {
        Optional<Carrito> carritoOpt = carritoRepository.findByUserId(usuario.getId());
        return carritoOpt.map(Carrito::getProductos).orElse(List.of());
    }
}
