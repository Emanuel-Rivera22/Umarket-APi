 package io.umarket.admin;

import io.umarket.model.Producto;
import io.umarket.service.ProductoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar los productos desde el panel de administración.
 * Permite listar, crear, editar y eliminar productos.
 */
@Controller
@RequestMapping("/admin/productos")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductoController {

    private final ProductoService productoService;

    public AdminProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // 📋 LISTAR TODOS LOS PRODUCTOS
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "admin/productos/lista"; // → templates/admin/productos/lista.html
    }

    // ➕ FORMULARIO NUEVO PRODUCTO
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/productos/form"; // → templates/admin/productos/form.html
    }

    // 💾 GUARDAR NUEVO O EDITADO
    @PostMapping
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/admin/productos";
    }

    // ✏️ FORMULARIO DE EDICIÓN
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        Producto producto = productoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de producto no válido: " + id));
        model.addAttribute("producto", producto);
        return "admin/productos/form";
    }

    // ❌ ELIMINAR PRODUCTO
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos";
    }
}
