package ru.abrosimov.bookshop.services;

import ru.abrosimov.bookshop.dao.BookDao;
import ru.abrosimov.bookshop.dao.impl.BookDaoImpl;
import ru.abrosimov.bookshop.models.Book;

import java.util.List;
public class BookService {
    private BookDao booksDao = new BookDaoImpl();

    public void createBook(Book book) {
        booksDao.create(book);
    }

    public void deleteBook(Book book) {
        booksDao.delete(book);
    }

    public void updateBook(Book book) {
        booksDao.update(book);
    }

    public Book readBookByID(int id) { return booksDao.readByID(id); }

    public List<Book> readBooksListByGenre(String genre) {
        return booksDao.readListByGenre(genre);
    }

    public List<Book> readBooksListByName(String title) {
        return booksDao.readListByName(title);
    }
}