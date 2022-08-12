package ru.abrosimov.bookshop.dao;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;

import java.util.List;

public interface OrderDao {
    void create(Order order);
    void update(Order order);
    void delete(Order order);
    Order readByID(int id);
    List<Order> readOrdersByCustomer(Customer customer);
}
