package io.umarket.service;

import io.umarket.model.Producto;
import io.umarket.repository.ProductoRepository; 
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // 1. ✅ MÉTODO MODIFICADO: Ahora se llama listarProductos()
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    
    // 2. ✅ MÉTODO CONFIRMADO: El controlador lo usa así
    public Optional<Producto> buscarPorId(String id) {
        return productoRepository.findById(id);
    }
    
    // 3. ✅ MÉTODO MODIFICADO: Ahora se llama guardarProducto()
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // 4. ✅ MÉTODO MODIFICADO: Ahora se llama eliminarProducto()
    public void eliminarProducto(String id) {
        productoRepository.deleteById(id);
    }
}