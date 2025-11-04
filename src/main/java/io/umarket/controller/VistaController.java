package io.umarket.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.service.CarritoService;
import io.umarket.service.ProductoService;
import io.umarket.service.UsuarioService;

@Controller
@RequestMapping("/")
public class VistaController {

    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final CarritoService carritoService;

    public VistaController(ProductoService productoService, UsuarioService usuarioService, CarritoService carritoService) {
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.carritoService = carritoService;
    }

    /**
     * Helper para agregar el carrito y usuario al modelo, manejando el caso de
     * usuario no logueado/no encontrado.
     */
    private void agregarCarritoYUsuarioAlModelo(Model model, Principal principal) {
        Carrito carrito = null;
        Usuario usuario = null;

        if (principal != null) {
            Optional<Usuario> userOptional = usuarioService.buscarPorNombre(principal.getName());
            if (userOptional.isPresent()) {
                usuario = userOptional.get();
                // Usamos la ID del usuario para obtener o crear el carrito
                carrito = carritoService.obtenerOCrearCarrito(usuario.getId());
            }
        }

        // Si no hay usuario o principal, 'carrito' y 'usuario' serán null, lo cual es manejado por el HTML.
        model.addAttribute("carrito", carrito);
        model.addAttribute("usuario", usuario);
    }

    @GetMapping
    public String verProductos(Model model, Principal principal) {
        List<Producto> productos = productoService.listarProductos();

        model.addAttribute("productos", productos);
        agregarCarritoYUsuarioAlModelo(model, principal);

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal, @RequestParam(value = "error", required = false) String error) {
        if (principal != null) {
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("mensajeError", "Nombre de usuario o contraseña incorrectos.");
        }
        return "login";
    }

    /*
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
     */
    @GetMapping("/detail/{id}")
    public String verDetalleProducto(@PathVariable String id, Model model, Principal principal) {
        Optional<Producto> productoOpt = productoService.buscarPorId(id);
        if (productoOpt.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("producto", productoOpt.get());
        agregarCarritoYUsuarioAlModelo(model, principal);

        return "detail";
    }

}
