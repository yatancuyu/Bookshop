package dao;

import models.BookOrder;


public interface BookOrderDao {
    void create(BookOrder bookOrder);
    void update(BookOrder bookOrder);
    void delete(BookOrder bookOrder);
    BookOrder readByID(int bookId, int orderId);
}
