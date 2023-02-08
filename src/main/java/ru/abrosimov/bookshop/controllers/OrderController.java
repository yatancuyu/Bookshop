package ru.abrosimov.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.abrosimov.bookshop.dao.BookDAO;
import ru.abrosimov.bookshop.dao.BookOrderDAO;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.dao.OrderDAO;
import ru.abrosimov.bookshop.models.Book;
import ru.abrosimov.bookshop.models.BookOrderEntity;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;

import java.util.Date;
import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BookOrderDAO bookOrderDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/order/{id}")
    public String showOrder(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                            @PathVariable("id") Integer orderId,
                            Model model) {
        Optional<Order> order = orderDAO.findById(orderId);
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));

        model.addAttribute("ableToCheckout", Integer.parseInt(customerId) == order.get().getCustomer().getId());
        model.addAttribute("auth", customer.isPresent());
        model.addAttribute("admin", customer.isPresent() && customer.get().isAdminRights());
        model.addAttribute("order", order.get());
        model.addAttribute("openStatus", Order.Status.OPEN);
        return "order";
    }

    @GetMapping("/order/checkout")
    public String showOrderCheckoutConfirmation(@RequestParam("orderId") Integer orderId, Model model) {
        model.addAttribute(orderDAO.findById(orderId).get());
        return "orderCheckout";
    }

    @PostMapping("/order/checkout")
    public String checkoutOrder(@RequestParam("orderId") Integer orderId,
                                @RequestParam("deliveryAddress") String deliveryAddress,
                                @RequestParam("datetimeOfDelivery") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Order order = orderDAO.findById(orderId).get();
        order.setDeliveryAddress(deliveryAddress);
        order.setDatetimeOfDelivery(date);
        order.setStatus(Order.Status.CLOSED);
        System.out.println(order);
        orderDAO.update(order);
        return "redirect:/order/" + order.getId();
    }

    @PostMapping("/order/book")
    public String orderBook(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                            @RequestParam("bookId") Integer bookId,
                            @RequestParam("orderId") Integer orderId) {
        Book book = bookDAO.findById(bookId).get();
        if (book.getAmount() == 0) {
            return "error";
        }
        book.setAmount(book.getAmount() - 1);
        bookDAO.update(book);
        Customer customer = customerDAO.findById(Integer.parseInt(customerId)).get();
        Order order = orderDAO.findById(orderId).orElseGet(() -> new Order(customer, book.getPrice()));

        if (orderId != 0) {
            order.setPrice(order.getPrice() + book.getPrice());
            orderDAO.update(order);

            for (BookOrderEntity bookOrderEntity : order.getBooks()) {
                System.out.println(bookOrderEntity.getBook());
                if (bookOrderEntity.getBook().equals(book)) {
                    bookOrderEntity.setAmount(bookOrderEntity.getAmount() + 1);
                    bookOrderDAO.update(bookOrderEntity);
                    return "redirect:/order/" + orderId;
                }
            }
        } else {
            orderDAO.save(order);
        }
        bookOrderDAO.save(new BookOrderEntity(book, order, 1));
        return "redirect:/order/" + order.getId();
    }

    @GetMapping("/order/all")
    private String showAllOrders(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                                 Model model) {
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));

        model.addAttribute("auth", customer.isPresent());
        boolean isAdmin = customer.isPresent() && customer.get().isAdminRights();
        if (!isAdmin) {
            return "error";
        }
        model.addAttribute("admin", isAdmin);
        model.addAttribute("orderDAO", orderDAO);
        return "allOrders";
    }
}
