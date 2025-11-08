package io.umarket.controller;

<<<<<<< HEAD
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

=======
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
import io.umarket.model.Carrito;
import io.umarket.model.Producto;
import io.umarket.model.Usuario;
import io.umarket.service.CarritoService;
import io.umarket.service.ProductoService;
import io.umarket.service.UsuarioService;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7

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
<<<<<<< HEAD
     * Helper para agregar el carrito y usuario al modelo, manejando el caso de
     * usuario no logueado/no encontrado.
=======
     * Helper para agregar el carrito y usuario al modelo, manejando el caso de usuario no logueado/no encontrado.
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
     */
    private void agregarCarritoYUsuarioAlModelo(Model model, Principal principal) {
        Carrito carrito = null;
        Usuario usuario = null;

        if (principal != null) {
            Optional<Usuario> userOptional = usuarioService.buscarPorNombre(principal.getName());
            if (userOptional.isPresent()) {
                usuario = userOptional.get();
                // Usamos la ID del usuario para obtener o crear el carrito
<<<<<<< HEAD
                carrito = carritoService.obtenerOCrearCarrito(usuario.getId());
            }
        }

=======
                carrito = carritoService.obtenerOCrearCarrito(usuario.getId()); 
            }
        }
        
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
        // Si no hay usuario o principal, 'carrito' y 'usuario' serán null, lo cual es manejado por el HTML.
        model.addAttribute("carrito", carrito);
        model.addAttribute("usuario", usuario);
    }

    @GetMapping
    public String verProductos(Model model, Principal principal) {
<<<<<<< HEAD
        List<Producto> productos = productoService.listarProductos();

        model.addAttribute("productos", productos);
        agregarCarritoYUsuarioAlModelo(model, principal);

=======
        List<Producto> productos = productoService.listarProductos(); 
        
        model.addAttribute("productos", productos);
        agregarCarritoYUsuarioAlModelo(model, principal);
        
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
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

<<<<<<< HEAD
    /*
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
     */
=======
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
    @GetMapping("/detail/{id}")
    public String verDetalleProducto(@PathVariable String id, Model model, Principal principal) {
        Optional<Producto> productoOpt = productoService.buscarPorId(id);
        if (productoOpt.isEmpty()) {
            return "redirect:/";
        }
<<<<<<< HEAD

        model.addAttribute("producto", productoOpt.get());
        agregarCarritoYUsuarioAlModelo(model, principal);

        return "detail";
    }

}
=======
        
        model.addAttribute("producto", productoOpt.get());
        agregarCarritoYUsuarioAlModelo(model, principal);
        
        return "detail";
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model, Principal principal) {
        if (principal == null) {
            // Requerir inicio de sesión para ver el carrito
            return "redirect:/login"; 
        }
        
        // Carga el carrito y el usuario al modelo
        agregarCarritoYUsuarioAlModelo(model, principal);
        
        // Mantiene los mensajes de éxito/error después de las redirecciones
        if (model.containsAttribute("mensajeExito")) {
            model.addAttribute("mensajeExito", model.asMap().get("mensajeExito"));
        }
        if (model.containsAttribute("mensajeError")) {
            model.addAttribute("mensajeError", model.asMap().get("mensajeError"));
        }
        
        return "carrito";
    }
}
>>>>>>> f584651063845f4c71ddecb46a261304fe6341b7
