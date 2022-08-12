package ru.abrosimov.bookshop.services;

import ru.abrosimov.bookshop.models.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class CustomerServiceTest {

    @Test
    public void createCustomer() {
        CustomerService customerService = new CustomerService();
        Customer newCustomer = new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov", "qwerty", false);
        customerService.createCustomer(newCustomer);
        Customer checkCustomer = customerService.readCustomerByLogin("ivanov");
        Assert.assertEquals(newCustomer, checkCustomer);
        customerService.deleteCustomer(newCustomer);
    }

    @Test
    public void updateCustomer() {
        CustomerService customerService = new CustomerService();
        Customer newCustomer = new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov", "qwerty", false);
        customerService.createCustomer(newCustomer);
        newCustomer.setPhoneNumber("89998881111");
        customerService.updateCustomer(newCustomer);
        Customer checkCustomer = customerService.readCustomerByLogin("ivanov");
        Assert.assertEquals(newCustomer, checkCustomer);
        customerService.deleteCustomer(newCustomer);
    }

    @Test
    public void readCustomerByID() {
        CustomerService customerService = new CustomerService();
        Customer newCustomer = new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov", "qwerty", false);
        customerService.createCustomer(newCustomer);
        Customer checkCustomer = customerService.readCustomerByID(newCustomer.getId());
        Assert.assertEquals(newCustomer, checkCustomer);
        customerService.deleteCustomer(newCustomer);
    }

    @Test
    public void readCustomers() {
        CustomerService customerService = new CustomerService();
        List<Customer> customers = List.of(new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov1", "qwerty", false),
                new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                        "г. Москва, ул. Ломоносовский проспект, д. 2", "ivanov2", "qwerty", false),
                new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                        "г. Москва, ул. Ломоносовский проспект, д. 3", "ivanov3", "qwerty", false),
                new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                        "г. Москва, ул. Ломоносовский проспект, д. 4", "ivanov4", "qwerty", false));
        for (Customer customer : customers) {
            customerService.createCustomer(customer);
        }
        List<Customer> checkCustomers = customerService.readCustomers();
        Assert.assertEquals(customers, checkCustomers);
    }
}