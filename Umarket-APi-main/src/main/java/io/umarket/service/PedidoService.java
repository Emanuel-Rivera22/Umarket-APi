package io.umarket.service;

import io.umarket.model.Pedido;
import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido obtenerOCrearPedido(String userId) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findByUserId(userId);
        return pedidoOpt.orElseGet(() -> crearNuevoPedido(userId));
    }

    private Pedido crearNuevoPedido(String userId) {
        Pedido nuevoPedido = new Pedido(userId);
        return pedidoRepository.save(nuevoPedido);
    }

    public void agregarProducto(String userId, Producto producto) {
        Pedido pedido = obtenerOCrearPedido(userId);
        pedido.agregarProducto(producto);
        pedidoRepository.save(pedido);
    }

    public void eliminarProductoPorId(String userId, String productoId) {
        Pedido pedido = obtenerOCrearPedido(userId);
        pedido.eliminarProductoPorId(productoId);
        pedidoRepository.save(pedido);
    }

    public List<Producto> obtenerProductosDelUsuario(Usuario usuario) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findByUserId(usuario.getId());
        return pedidoOpt.map(Pedido::getProductos).orElse(Collections.emptyList());
    }
}
