package ru.abrosimov.bookshop.services;

import ru.abrosimov.bookshop.dao.CustomerDao;
import ru.abrosimov.bookshop.dao.impl.CustomerDaoImpl;
import ru.abrosimov.bookshop.models.Customer;

import java.util.List;

public class CustomerService {
    private CustomerDao customersDao = new CustomerDaoImpl();
    public void createCustomer(Customer customer) {
        customersDao.create(customer);
    }

    public void deleteCustomer(Customer customer) {
        customersDao.delete(customer);
    }

    public void updateCustomer(Customer customer) {
        customersDao.update(customer);
    }

    public Customer readCustomerByID(int id) {
        return customersDao.readByID(id);
    }

    public Customer readCustomerByLogin(String login) {
        return customersDao.readByLogin(login);
    }

    public List<Customer> readCustomers() {
        return customersDao.readCustomers();
}
}
