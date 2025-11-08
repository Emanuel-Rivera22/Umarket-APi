package io.umarket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Pedidos")
public class Pedido {

    @Id
    private String id; // ID del documento de MongoDB
    
    private String userId; // ID del Usuario al que pertenece este carrito (clave de búsqueda)
    
    // Lista de productos. Asumo que guardas el producto completo o un DTO/referencia.
    private List<Producto> productos; 

    // Constructor vacío (necesario para Spring Data)
    public Pedido() {
        this.productos = new ArrayList<>();
    }

    // CONSTRUCTOR Usado en CarritoService#crearNuevoCarrito
    public Pedido(String userId) {
        this.userId = userId;
        this.productos = new ArrayList<>();
    }

    // --- MÉTODOS DE LÓGICA DE NEGOCIO REQUERIDOS POR EL SERVICIO ---

    //MÉTODO Usado en CarritoService#agregarProducto
    public void agregarProducto(Producto producto) {
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
        
        this.productos.add(producto);
    }

    // MÉTODO Usado en CarritoService#eliminarProductoPorId
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