package io.umarket.service;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.repository.CarritoRepository; // Asegúrate de que existe
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    // ✅ MÉTODO REQUERIDO: Implementación de obtenerOCrearCarrito
    public Carrito obtenerOCrearCarrito(String userId) {
        // Busca el carrito por el ID de usuario. Si no existe, crea uno nuevo.
        Optional<Carrito> carritoOpt = carritoRepository.findByUserId(userId);
        return carritoOpt.orElseGet(() -> crearNuevoCarrito(userId));
    }
    
    private Carrito crearNuevoCarrito(String userId) {
        Carrito nuevoCarrito = new Carrito(userId);
        return carritoRepository.save(nuevoCarrito);
    }

    // ✅ MÉTODO REQUERIDO: Implementación de agregarProducto (usado en CarritoController)
    public void agregarProducto(String userId, Producto producto) {
        // Lógica para añadir el producto al carrito del usuario y guardarlo
        Carrito carrito = obtenerOCrearCarrito(userId);
        carrito.agregarProducto(producto); // Asumiendo que el modelo Carrito tiene este método
        carritoRepository.save(carrito);
    }

    // ✅ MÉTODO REQUERIDO: Implementación de eliminarProductoPorId (usado en CarritoController)
    public void eliminarProductoPorId(String userId, String productoId) {
        // Lógica para encontrar el carrito, eliminar el producto por ID y guardar
        Carrito carrito = obtenerOCrearCarrito(userId);
        carrito.eliminarProductoPorId(productoId); // Asumiendo que el modelo Carrito tiene este método
        carritoRepository.save(carrito);
    }

    // Asumiendo que tienes un método findByUserId en tu CarritoRepository
}