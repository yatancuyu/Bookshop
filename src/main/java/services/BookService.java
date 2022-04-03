package services;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import models.Book;

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