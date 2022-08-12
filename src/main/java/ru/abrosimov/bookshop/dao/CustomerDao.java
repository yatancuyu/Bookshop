package ru.abrosimov.bookshop.dao;

import ru.abrosimov.bookshop.models.Customer;

import java.util.List;

public interface CustomerDao {
    void create(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    Customer readByID(int id);
    Customer readByLogin(String login);
    List<Customer> readCustomers();
}
