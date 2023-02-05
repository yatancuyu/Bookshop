package ru.abrosimov.bookshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.abrosimov.bookshop.dao.BookDAO;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.models.Book;
import ru.abrosimov.bookshop.models.Customer;

import java.util.Optional;

@Controller
public class BookController {
    private final BookDAO bookDAO;
    private final CustomerDAO customerDAO;

    BookController(BookDAO bookDAO, CustomerDAO customerDAO) {
        this.bookDAO = bookDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping("/book/{id}")
    public String showBook(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                           @PathVariable("id") Integer bookId,
                           Model model) {
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));
        model.addAttribute("auth", customer.isPresent());
        model.addAttribute("admin", customer.isPresent() && customer.get().isAdminRights());

        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            model.addAttribute("msg", "Book not found");
            return "error";
        }

        model.addAttribute("book", book.get());
        return "book";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam(name = "bookId") Integer bookId,
                             @CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                             Model model) {
        checkBook(customerId, bookId, model);

        bookDAO.delete(bookDAO.findById(bookId).get());
        return "redirect:/";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam(name = "bookId") Integer bookId,
                           @CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                           Model model) {
        String err = checkBook(customerId, bookId, model);
        if (err != null) {
            return err;
        }

        model.addAttribute("book", bookDAO.findById(bookId).get());
        return "editBook";
    }

    @PostMapping("/book/edit")
    public String editBook(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                           @ModelAttribute("book") Book book,
                           Model model) {
        String err = checkBook(customerId, book.getId(), model);
        if (err != null) {
            return err;
        }

        bookDAO.update(book);
        return "redirect:/book/" + book.getId();
    }

    private String checkBook(String customerId, Integer bookId, Model model) {
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));
        if (customer.isEmpty() || !customer.get().isAdminRights()) {
            model.addAttribute("msg", "Permission denied.");
            return "error";
        }
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            model.addAttribute("msg", "Book not found");
            return "error";
        }
        return null;
    }

}
