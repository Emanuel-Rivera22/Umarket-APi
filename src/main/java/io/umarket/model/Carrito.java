package io.umarket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carritos")
public class Carrito {

    @Id
    private String id; // ID del documento de MongoDB
    
    private String userId; // ID del Usuario al que pertenece este carrito (clave de búsqueda)
    
    // Lista de productos. Asumo que guardas el producto completo o un DTO/referencia.
    private List<Producto> productos; 

    // Constructor vacío (necesario para Spring Data)
    public Carrito() {
        this.productos = new ArrayList<>();
    }

    // ✅ CONSTRUCTOR REQUERIDO: Usado en CarritoService#crearNuevoCarrito
    public Carrito(String userId) {
        this.userId = userId;
        this.productos = new ArrayList<>();
    }

    // --- MÉTODOS DE LÓGICA DE NEGOCIO REQUERIDOS POR EL SERVICIO ---

    // ✅ MÉTODO REQUERIDO: Usado en CarritoService#agregarProducto
    public void agregarProducto(Producto producto) {
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
        // Nota: Aquí podrías añadir lógica para sumar cantidades si el producto ya existe.
        this.productos.add(producto);
    }

    // ✅ MÉTODO REQUERIDO: Usado en CarritoService#eliminarProductoPorId
    public void eliminarProductoPorId(String productoId) {
        if (this.productos != null) {
            // Elimina la primera ocurrencia del producto con el ID coincidente
            this.productos.removeIf(p -> p.getId().equals(productoId));
        }
    }

    // --- GETTERS Y SETTERS ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}