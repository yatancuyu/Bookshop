package services;

import dao.BookOrderDao;
import dao.impl.BookOrderDaoImpl;
import models.Book;
import models.BookOrder;

import java.util.List;

public class BookOrderService {
    private BookOrderDao bookOrderDao = new BookOrderDaoImpl();

    public void createBookOrder(BookOrder bookOrder) { bookOrderDao.create(bookOrder); }
    public void updateBookOrder(BookOrder bookOrder) { bookOrderDao.update(bookOrder); }
    public void deleteBookOrder(BookOrder bookOrder) { bookOrderDao.delete(bookOrder); }
    public BookOrder readByID(int bookId, int orderId) { return bookOrderDao.readByID(bookId, orderId); }
}
