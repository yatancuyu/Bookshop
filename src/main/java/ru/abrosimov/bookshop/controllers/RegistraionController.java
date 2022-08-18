package ru.abrosimov.bookshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.models.Customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/registration")
public class RegistraionController {

    private final CustomerDAO customerDAO;

    @Autowired
    public RegistraionController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping()
    public String showRegistrationForm(@ModelAttribute("customer") Customer customer,
                                       Model model) {
        return "registration";
    }

    @PostMapping()
    public String createCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                 BindingResult bindingResult,
                                 HttpServletResponse httpResponse) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        httpResponse.addCookie(new Cookie("login", customer.getLogin()));
        httpResponse.addCookie(new Cookie("password", customer.getPassword()));
        customerDAO.save(customer);
        return "redirect:/";
    }
}
