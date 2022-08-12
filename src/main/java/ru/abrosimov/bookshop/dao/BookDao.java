package ru.abrosimov.bookshop.dao;

import ru.abrosimov.bookshop.models.Book;

import java.util.List;

public interface BookDao {
    void create(Book book);
    void update(Book book);
    void delete(Book book);
    Book readByID(int id);
    List<Book> readListByGenre(String genre);
    List<Book> readListByName(String title);
}
