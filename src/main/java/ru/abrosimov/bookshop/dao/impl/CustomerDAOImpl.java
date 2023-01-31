package ru.abrosimov.bookshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.models.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

@Repository
public class CustomerDAOImpl extends GenericDAOImpl<Customer>
        implements CustomerDAO {

    @Override
    public Optional<Customer> findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customer> query = session.createQuery(
                    "FROM Customer WHERE login = :param", Customer.class)
                    .setParameter("param", login);
            if (query.getResultList().size() == 0) {
                return Optional.empty();
            }
            return applyInitialize(Optional.ofNullable(query.getResultList().get(0)));
        }
    }

    public boolean isAuthenticated(String login, String password) {
        Optional<Customer> customerOp = findByLogin(login);
        if (customerOp.isEmpty())
            return false;

        Customer customer = customerOp.get();
        return customer.getPassword().equals(password);
    }

    public boolean isAdmin(String login, String password) {
        Optional<Customer> customerOp = findByLogin(login);
        if (customerOp.isEmpty())
            return false;

        Customer customer = customerOp.get();
        return customer.getPassword().equals(password) &&
                customer.isAdminRights();
    }
}
