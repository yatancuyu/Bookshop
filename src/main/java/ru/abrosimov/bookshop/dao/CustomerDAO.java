package ru.abrosimov.bookshop.dao;

import ru.abrosimov.bookshop.models.Customer;

import java.util.Optional;

public interface CustomerDAO extends GenericDAO<Customer>{
    Optional<Customer> findByLogin(String login);

    boolean Authentification(String login, String password);
}
