package io.umarket.service;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
<<<<<<< HEAD
import io.umarket.model.Usuario;
import io.umarket.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
=======
import io.umarket.repository.CarritoRepository; // Asegúrate de que existe
import org.springframework.stereotype.Service;

>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

<<<<<<< HEAD
    /**
     * Obtiene el carrito de un usuario existente o crea uno nuevo si no existe.
     */
    public Carrito obtenerOCrearCarrito(String userId) {
        Optional<Carrito> carritoOpt = carritoRepository.findByUserId(userId);
        return carritoOpt.orElseGet(() -> crearNuevoCarrito(userId));
    }

=======
    // ✅ MÉTODO REQUERIDO: Implementación de obtenerOCrearCarrito
    public Carrito obtenerOCrearCarrito(String userId) {
        // Busca el carrito por el ID de usuario. Si no existe, crea uno nuevo.
        Optional<Carrito> carritoOpt = carritoRepository.findByUserId(userId);
        return carritoOpt.orElseGet(() -> crearNuevoCarrito(userId));
    }
    
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
    private Carrito crearNuevoCarrito(String userId) {
        Carrito nuevoCarrito = new Carrito(userId);
        return carritoRepository.save(nuevoCarrito);
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
