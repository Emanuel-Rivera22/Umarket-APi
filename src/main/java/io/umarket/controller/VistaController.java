package io.umarket.controller;

import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.service.CarritoService;
import io.umarket.service.ProductoService;
import io.umarket.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.context.SecurityContextHolder; 

import java.security.Principal;
import java.util.List;

@Controller
public class VistaController {

    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final CarritoService carritoService;

    public VistaController(UsuarioService usuarioService, ProductoService productoService, CarritoService carritoService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.carritoService = carritoService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Producto> productos = productoService.listarProductos();
        model.addAttribute("productos", productos);

        if (principal != null) {
            String username = principal.getName();
            
            usuarioService.buscarPorNombre(username).ifPresent(usuario -> {
                model.addAttribute("usuario", usuario);
                Carrito carritoActual = carritoService.obtenerCarritoPorUsuario(usuario.getId())
                        .orElse(new Carrito(usuario.getId())); 
                        
                model.addAttribute("carrito", carritoActual);
            });
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && 
            authentication.isAuthenticated() && 
            !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario); 
        return "redirect:/login";
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            usuarioService.buscarPorNombre(username).ifPresent(usuario -> {
                Carrito carrito = carritoService.obtenerCarritoPorUsuario(usuario.getId())
                        .orElseGet(() -> carritoService.crearCarrito(usuario.getId()));
                model.addAttribute("carrito", carrito);
                model.addAttribute("usuario", usuario);
            });
        }
        return "carrito";
    }

    @PostMapping("/carrito/agregar/{productoId}")
    public String agregarAlCarrito(@PathVariable String productoId, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            usuarioService.buscarPorNombre(username).ifPresent(usuario -> {
                productoService.buscarPorId(productoId).ifPresent(producto ->
                        carritoService.agregarProducto(usuario.getId(), producto));
            });
        }
        return "redirect:/";
    }

    // ✅ CORRECCIÓN: Usa el nuevo método del servicio para eliminar por ID.
    @PostMapping("/carrito/eliminar/{productoId}")
    public String eliminarDelCarrito(@PathVariable String productoId, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            usuarioService.buscarPorNombre(username).ifPresent(usuario -> {
                carritoService.eliminarProductoPorId(usuario.getId(), productoId);
            });
        }
        return "redirect:/carrito";
    }
}