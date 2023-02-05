package ru.abrosimov.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.dao.OrderDAO;
import ru.abrosimov.bookshop.models.Customer;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping({"/customer/{id}", "/customer"})
    public String showCustomer(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                               @PathVariable(value = "id", required = false) Integer id,
                               Model model) {
        if (id == null) {
            id = Integer.parseInt(customerId);
        }

        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));
        model.addAttribute("auth", customer.isPresent());
        boolean adminRights = customer.isPresent() && customer.get().isAdminRights();
        model.addAttribute("admin", adminRights);

        if (Integer.parseInt(customerId) != id && !adminRights) {
            System.out.println(customer.isPresent());
            model.addAttribute("msg", "Permission denied");
            return "error";
        }

        model.addAttribute("customer", customerDAO.findById(id).get());
        model.addAttribute("orderDAO", orderDAO);
        return "customer";
    }


    @PostMapping("/customer/edit")
    public String editCustomer(@ModelAttribute("customer") @Valid Customer customer,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer";
        }

        customerDAO.update(customer);
        return "redirect:/customer/" + customer.getId();
    }
}
