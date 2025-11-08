package io.umarket.controller;

import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.service.PedidoService;
import io.umarket.service.ProductoService;
import io.umarket.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public PedidoController(PedidoService pedidoService, UsuarioService usuarioService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    /**
     * Muestra los pedidos (carrito) del usuario logueado.
     */
    @GetMapping("/pedidos")
    public String verPedidos(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("productos", List.of());
            model.addAttribute("mensaje", "Inicia sesión para guardar tus productos en el carrito.");
            return "pedidos";
        }

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorNombre(principal.getName());
        if (usuarioOpt.isEmpty()) {
            model.addAttribute("productos", List.of());
            model.addAttribute("mensaje", "Usuario no encontrado.");
            return "pedidos";
        }

        Usuario usuario = usuarioOpt.get();
        List<Producto> productos = pedidoService.obtenerProductosDelUsuario(usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("usuario", usuario);

        if (productos.isEmpty()) {
            model.addAttribute("mensaje", "Tu carrito está vacío.");
        }

        return "pedidos";
    }

    /**
     * Agrega un producto al pedido del usuario logueado.
     */
    @PostMapping("/pedidos/agregar/{productoId}")
    public String agregarAlPedido(@PathVariable String productoId,
                                  Principal principal,
                                  RedirectAttributes redirectAttributes) {

        if (principal == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Debes iniciar sesión para añadir productos.");
            return "redirect:/login";
        }

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorNombre(principal.getName());
        Optional<Producto> productoOpt = productoService.buscarPorId(productoId);

        if (usuarioOpt.isEmpty() || productoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al encontrar usuario o producto.");
            return "redirect:/";
        }

        Usuario usuario = usuarioOpt.get();
        Producto producto = productoOpt.get();

        try {
            pedidoService.agregarProducto(usuario.getId(), producto);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto añadido al carrito con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo añadir el producto al carrito.");
        }

        return "redirect:/pedidos";
    }

    /**
     * Elimina un producto del pedido del usuario logueado.
     */
    @PostMapping("/pedidos/eliminar/{productoId}")
    public String eliminarDelPedido(@PathVariable String productoId,
                                    Principal principal,
                                    RedirectAttributes redirectAttributes) {

        if (principal == null) {
            return "redirect:/login";
        }

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorNombre(principal.getName());

        if (usuarioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error: Usuario no encontrado.");
            return "redirect:/";
        }

        Usuario usuario = usuarioOpt.get();

        try {
            pedidoService.eliminarProductoPorId(usuario.getId(), productoId);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado del carrito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo eliminar el producto del carrito.");
        }

        return "redirect:/pedidos";
    }
}
