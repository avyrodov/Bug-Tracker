package com.avyrodov.bugTracker.web.login;

import com.avyrodov.bugTracker.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class RegistrationController {
    private final IUserService userService;

    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("command")
    public RegistrationForm getForm() {
        return new RegistrationForm();
    }

    @GetMapping(value = "/registration.html")
    public String showForm(@ModelAttribute("command") RegistrationForm form, Model model) {
        model.addAttribute("command", form);
        return "auth/registration";
    }

    @PostMapping(value = "/registration.html")
    public String submitForm(@ModelAttribute("command") RegistrationForm form, HttpServletRequest request) {
        userService.save(form);
        request.setAttribute("username", form.getUsername());
        return "redirect:/login.html";
    }
}
