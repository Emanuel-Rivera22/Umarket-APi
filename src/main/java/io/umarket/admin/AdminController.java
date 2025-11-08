package io.umarket.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("titulo", "Panel de Administración");
        model.addAttribute("mensaje", "Bienvenido, administrador.");
        return "admin-dashboard"; // Thymeleaf busca /templates/admin-dashboard.html
    }
}
