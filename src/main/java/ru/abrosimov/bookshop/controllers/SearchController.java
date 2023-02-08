package ru.abrosimov.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abrosimov.bookshop.dao.BookDAO;
import ru.abrosimov.bookshop.models.Book;

import java.util.Arrays;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/search")
    private String showSearchResults(@RequestParam("filter") Integer filterNo,
                                     @RequestParam("request") String request,
                                     Model model) {
        Filter[] test = {this::searchByGenre, this::searchByName, this::searchByAuthor, this::searchByYear};
        model.addAttribute("result", test[filterNo - 1].search(request));
        return "search";
    }

    interface Filter {
        List<Book> search(String line);
    }

    private List<Book> searchByGenre(String request) {
        return bookDAO.findAll().stream().filter(book -> book.getGenre().contains(request)).toList();
    }

    private List<Book> searchByAuthor(String request) {
        return bookDAO.findAll().stream().filter(book -> book.getAuthors().contains(request)).toList();
    }

    private List<Book> searchByYear(String request) {
        return bookDAO.findAll().stream().filter(book -> book.getYearOfPublication().equals(Integer.parseInt(request))).toList();
    }

    private List<Book> searchByName(String request) {
        return bookDAO.findAll().stream().filter(book -> book.getName().contains(request)).toList();
    }
}
