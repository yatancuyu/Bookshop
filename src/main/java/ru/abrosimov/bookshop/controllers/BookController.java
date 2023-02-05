package ru.abrosimov.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/book/{id}")
    public String showBook(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                           @PathVariable("id") Integer bookId,
                           Model model) {
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));
        model.addAttribute("auth", customer.isPresent());
        model.addAttribute("admin", customer.isPresent() && customer.get().isAdminRights());

        String err = checkBook(bookId, model);
        if (err != null) {
            return err;
        }

        model.addAttribute("book", bookDAO.findById(bookId).get());
        return "book";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam(name = "bookId") Integer bookId,
                             @CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                             Model model) {
        check(customerId, bookId, model);

        bookDAO.delete(bookDAO.findById(bookId).get());
        return "redirect:/";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam(name = "bookId") Integer bookId,
                           @CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                           Model model) {
        String err = check(customerId, bookId, model);
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
        System.out.println(book);
        String err = check(customerId, book.getId(), model);

        if (err != null) {
            return err;
        }

        bookDAO.update(book);
        return "redirect:/book/" + book.getId();
    }

    @GetMapping("/book/create")
    public String createBook(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                             Model model) {
        String err = checkAdmin(customerId, model);
        if (err != null) {
            return err;
        }

        model.addAttribute("book", new Book());
        return "editBook";
    }

    @PostMapping("/book/create")
    public String createBook(@CookieValue(name = "customerId", defaultValue = "-1") String customerId,
                           @ModelAttribute("book") Book book,
                           Model model) {
        String err = checkAdmin(customerId, model);
        if (err != null) {
            return err;
        }

        bookDAO.save(book);
        return "redirect:/book/" + book.getId();
    }

    private String check(String customerId, Integer bookId, Model model) {
        if (checkAdmin(customerId, model) != null || checkBook(bookId, model) != null) {
            return "error";
        }
        return null;
    }

    private String checkBook(Integer bookId, Model model) {
        System.out.println(bookId);
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            model.addAttribute("msg", "Book not found");
            return "error";
        }
        return null;
    }

    private String checkAdmin(String customerId, Model model) {
        Optional<Customer> customer = customerDAO.findById(Integer.parseInt(customerId));
        if (customer.isEmpty() || !customer.get().isAdminRights()) {
            model.addAttribute("msg", "Permission denied.");
            return "error";
        }
        return null;
    }

}
