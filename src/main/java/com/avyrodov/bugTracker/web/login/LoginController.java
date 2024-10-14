package com.avyrodov.bugTracker.web.login;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class LoginController {
    @ModelAttribute("command")
    public LoginForm getForm(HttpServletRequest request) {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername(request.getParameter("username"));
        return loginForm;
    }

    @GetMapping("/login.html")
    public String showForm(@ModelAttribute("command") LoginForm form, Model model) {
        model.addAttribute("command", form);
        return "auth/login";
    }
}
