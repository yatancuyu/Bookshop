package ru.abrosimov.bookshop.services;

import ru.abrosimov.bookshop.dao.impl.OrderDaoImpl;
import ru.abrosimov.bookshop.dao.OrderDao;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;

import java.util.List;

public class OrderService {
    private OrderDao ordersDao = new OrderDaoImpl();
    public void createOrder(Order order) {
        ordersDao.create(order);
    }

    public List<Order> readOrdersByCustomer(Customer customer) {
        return ordersDao.readOrdersByCustomer(customer);
    }

    public void deleteOrder(Order order) {
        ordersDao.delete(order);
    }

    public void updateOrder(Order order) {
        ordersDao.update(order);
    }

    public Order readOrderByID(int id) {
        return ordersDao.readByID(id);
    }
}
