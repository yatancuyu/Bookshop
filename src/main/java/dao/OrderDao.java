package dao;
import models.Customer;
import models.Order;

import java.util.List;

public interface OrderDao {
    void create(Order order);
    void update(Order order);
    void delete(Order order);
    Order readByID(int id);
    List<Order> readOrdersByCustomer(Customer customer);
}
