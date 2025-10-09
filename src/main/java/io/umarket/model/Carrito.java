package io.umarket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carritos")
public class Carrito {

    @Id
    private String id;

    private String usuarioId;
    // ✅ CORRECCIÓN: Inicialización de la lista para evitar NullPointerException en vistas.
    private List<Producto> productos = new ArrayList<>(); 

    public Carrito() {}

    public Carrito(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }

    // Métodos de utilidad
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            this.productos.add(producto);
        }
    }

    public void eliminarProducto(Producto producto) {
        if (producto == null) return;
        // Remueve la primera coincidencia por ID
        this.productos.removeIf(p -> p.getId() != null && p.getId().equals(producto.getId())); 
    }

    // ✅ MÉTODO CLAVE: Permite eliminar desde el controlador de vistas
    public void eliminarProductoPorId(String productoId) {
        if (productoId == null) return;
        // Remueve la primera coincidencia por ID
        this.productos.removeIf(p -> productoId.equals(p.getId())); 
    }

    // ✅ MÉTODO CLAVE: Necesario para que Thymeleaf calcule el total
    public double calcularTotal() {
        return this.productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}