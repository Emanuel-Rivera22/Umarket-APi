package io.umarket.controller;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// ✅ CORRECCIÓN: Prefijo /api/v1 para evitar colisión con VistaController
@RequestMapping("/api/v1/carrito") 
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable String usuarioId) {
        return carritoService.obtenerCarritoPorUsuario(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{usuarioId}/agregar")
    public Carrito agregarProducto(@PathVariable String usuarioId, @RequestBody Producto producto) {
        return carritoService.agregarProducto(usuarioId, producto);
    }

    @PostMapping("/{usuarioId}/eliminar")
    public Carrito eliminarProducto(@PathVariable String usuarioId, @RequestBody Producto producto) {
        // Este endpoint REST espera el Producto completo en el cuerpo.
        return carritoService.eliminarProducto(usuarioId, producto);
    }

    @DeleteMapping("/{carritoId}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable String carritoId) {
        carritoService.eliminarCarrito(carritoId);
        return ResponseEntity.noContent().build();
    }
}