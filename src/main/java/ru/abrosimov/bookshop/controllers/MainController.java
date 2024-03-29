package ru.abrosimov.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.abrosimov.bookshop.dao.BookDAO;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.models.Customer;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CustomerDAO customerDAO;


    @GetMapping("/")
    public String showCatalog(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                              Model model) {
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));
        model.addAttribute("auth", customer.isPresent());
        model.addAttribute("admin", customer.isPresent() && customer.get().isAdminRights());
        model.addAttribute("bookDAO", bookDAO);
        return "main";
    }
}