package ru.abrosimov.bookshop.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.abrosimov.bookshop.dao.OrderDAO;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;

import java.util.Collection;
import java.util.Collections;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order>
        implements OrderDAO {

    @Override
    public Collection<Order> findForCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            if (customer == null) {
                return Collections.EMPTY_LIST;
            }
            Query<Order> query = session.createQuery(
                            "FROM Order WHERE customer = :param", Order.class)
                    .setParameter("param", customer);
            if (query.getResultList().size() == 0) {
                return Collections.EMPTY_LIST;
            }
            return applyInitialize(query.getResultList());
        }
    }
}
