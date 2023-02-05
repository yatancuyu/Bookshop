package ru.abrosimov.bookshop.dao;

import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;

import java.util.Collection;
import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    Collection<Order> findForCustomer(Customer customer);
}
