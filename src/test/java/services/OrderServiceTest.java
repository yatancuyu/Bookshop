package services;

import models.Customer;
import models.Order;
import org.junit.Assert;
import org.junit.Test;


public class OrderServiceTest {

    @Test
    public void createOrder() {
        OrderService orderService = new OrderService();
        CustomerService customerService = new CustomerService();
        Customer newCustomer = new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov", "qwerty", false);
        customerService.createCustomer(newCustomer);
        Order newOrder = new Order(newCustomer, 6569.90, null,
                null, Order.Status.OPEN);
        orderService.createOrder(newOrder);
        Order checkOrder = orderService.readOrdersByCustomer(newCustomer).get(0);
        Assert.assertEquals(newOrder, checkOrder);
        orderService.deleteOrder(newOrder);
        customerService.deleteCustomer(newCustomer);
    }


    @Test
    public void updateOrder() {
        OrderService orderService = new OrderService();
        CustomerService customerService = new CustomerService();
        Customer newCustomer = new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov", "qwerty", false);
        customerService.createCustomer(newCustomer);
        Order newOrder = new Order(newCustomer, 6569.90, null,
                null, Order.Status.OPEN);
        orderService.createOrder(newOrder);

        newOrder.setPrice(newOrder.getPrice() + 1000);
        orderService.updateOrder(newOrder);

        Order checkOrder = orderService.readOrderByID(newOrder.getId());
        Assert.assertEquals(newOrder, checkOrder);
        orderService.deleteOrder(newOrder);
        customerService.deleteCustomer(newCustomer);
    }
}