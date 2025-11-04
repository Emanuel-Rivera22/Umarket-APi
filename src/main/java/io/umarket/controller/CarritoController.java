package io.umarket.controller;

import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.service.CarritoService;
import io.umarket.service.ProductoService;
import io.umarket.service.UsuarioService;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
=======
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
import java.util.Optional;

@Controller
public class CarritoController {

    private final CarritoService carritoService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public CarritoController(CarritoService carritoService, UsuarioService usuarioService, ProductoService productoService) {
        this.carritoService = carritoService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    /**
<<<<<<< HEAD
     * Muestra el carrito del usuario. Si no está logueado, muestra vacío.
     */
    @GetMapping("/carrito")
    public String verCarrito(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("productos", List.of());
            model.addAttribute("mensaje", "Inicia sesión para guardar tus productos en el carrito.");
            return "carrito";
        }

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorNombre(principal.getName());
        if (usuarioOpt.isEmpty()) {
            model.addAttribute("productos", List.of());
            model.addAttribute("mensaje", "Usuario no encontrado.");
            return "carrito";
        }

        Usuario usuario = usuarioOpt.get();
        List<Producto> productos = carritoService.obtenerProductosDelUsuario(usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("usuario", usuario);

        if (productos.isEmpty()) {
            model.addAttribute("mensaje", "Tu carrito está vacío.");
        }

        return "carrito";
    }

    /**
=======
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
     * Agrega un producto al carrito del usuario logueado.
     */
    @PostMapping("/carrito/agregar/{productoId}")
    public String agregarAlCarrito(@PathVariable String productoId, 
<<<<<<< HEAD
                                   Principal principal, 
                                   RedirectAttributes redirectAttributes) {
=======
                                 Principal principal, 
                                 RedirectAttributes redirectAttributes) {
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
    
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
            carritoService.agregarProducto(usuario.getId(), producto);
<<<<<<< HEAD
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto añadido al carrito con éxito.");
        } catch (Exception e) {
=======
            // Ya estaba correcta
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto añadido al carrito con éxito.");
        } catch (Exception e) {
            // Ya estaba correcta
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo añadir el producto al carrito.");
        }
        
        return "redirect:/carrito"; 
    }
    
    /**
     * Elimina un producto del carrito del usuario logueado.
     */
    @PostMapping("/carrito/eliminar/{productoId}")
    public String eliminarDelCarrito(@PathVariable String productoId, 
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
<<<<<<< HEAD
            carritoService.eliminarProductoPorId(usuario.getId(), productoId);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado del carrito.");
        } catch (Exception e) {
=======
            // El servicio de carrito se encarga de buscar el producto y eliminarlo
            carritoService.eliminarProductoPorId(usuario.getId(), productoId);
            
            // 🛑 CORRECCIÓN APLICADA: Sintaxis de addFlashAttribute
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado del carrito.");
        } catch (Exception e) {
            // 🛑 CORRECCIÓN APLICADA: Sintaxis de addFlashAttribute
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo eliminar el producto del carrito.");
        }

        return "redirect:/carrito";
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
