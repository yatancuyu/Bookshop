package ru.abrosimov.bookshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.models.Customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthentificationController {
    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/registration")
    public String showRegistrationForm(@ModelAttribute("customer") Customer customer) {
        return "registration";
    }

    @PostMapping("/registration")
    public String createCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                 BindingResult bindingResult,
                                 HttpServletResponse httpResponse) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        customerDAO.save(customer);
        httpResponse.addCookie(new Cookie("customerId", String.valueOf(customer.getId())));
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLogInForm() {
        return "login";
    }

    @PostMapping("/login")
    public String logInCustomer(@RequestParam(name = "login") String login,
                                @RequestParam(name = "password") String password,
                                HttpServletResponse httpResponse) {
        if (!Authentification(login, password)) {
            return "login";
        }

        httpResponse.addCookie(new Cookie("customerId", String.valueOf(customerDAO.findByLogin(login).get().getId())));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logOutCustomer(HttpServletResponse httpResponse) {
        Cookie cookie = new Cookie("customerId", null);
        cookie.setMaxAge(0);
        httpResponse.addCookie(cookie);
        return "redirect:/";
    }

    public boolean Authentification(String login, String password) {
        Optional<Customer> customerOp = customerDAO.findByLogin(login);
        if (customerOp.isEmpty())
            return false;

        Customer customer = customerOp.get();
        return customer.getPassword().equals(password);
    }
}
