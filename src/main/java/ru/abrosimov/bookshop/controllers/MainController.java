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

    private final BookDAO bookDAO;
    private final CustomerDAO customerDAO;

    @Autowired
    public MainController(BookDAO bookDAO, CustomerDAO customerDAO) {
        this.bookDAO = bookDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping("/")
    public String showCatalog(@CookieValue(name="login", defaultValue = "NONE") String login,
                              @CookieValue(name="password", defaultValue = "NONE") String password,
                              Model model) {
        model.addAttribute("auth", customerDAO.isAuthenticated(login, password));
        model.addAttribute("admin", customerDAO.isAdmin(login, password));
        return "main";
    }
}