package services;

import dao.impl.OrderDaoImpl;
import dao.OrderDao;
import models.Customer;
import models.Order;

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
