package ru.abrosimov.bookshop.services;

import ru.abrosimov.bookshop.dao.BookOrderDao;
import ru.abrosimov.bookshop.dao.impl.BookOrderDaoImpl;
import ru.abrosimov.bookshop.models.BookOrder;

public class BookOrderService {
    private BookOrderDao bookOrderDao = new BookOrderDaoImpl();

    public void createBookOrder(BookOrder bookOrder) { bookOrderDao.create(bookOrder); }
    public void updateBookOrder(BookOrder bookOrder) { bookOrderDao.update(bookOrder); }
    public void deleteBookOrder(BookOrder bookOrder) { bookOrderDao.delete(bookOrder); }
    public BookOrder readByID(int bookId, int orderId) { return bookOrderDao.readByID(bookId, orderId); }
}
