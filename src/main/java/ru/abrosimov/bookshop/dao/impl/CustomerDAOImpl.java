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
            if (login == null) {
                return Optional.empty();
            }
            Query<Customer> query = session.createQuery(
                    "FROM Customer WHERE login = :param", Customer.class)
                    .setParameter("param", login);
            if (query.getResultList().size() == 0) {
                return Optional.empty();
            }
            return applyInitialize(Optional.ofNullable(query.getResultList().get(0)));
        }
    }


}
