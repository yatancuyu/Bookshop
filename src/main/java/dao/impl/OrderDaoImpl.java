package dao.impl;

import dao.OrderDao;
import models.Customer;
import models.Order;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void create(Order order) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(order);
            tx1.commit();
        }
    }

    @Override
    public void update(Order order) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(order);
            tx1.commit();
        }
    }

    @Override
    public void delete(Order order) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(order);
            tx1.commit();
        }
    }

    @Override
    public Order readByID(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Order.class, id);
        }
    }

    @Override
    public List<Order> readOrdersByCustomer(Customer customer) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("FROM Order WHERE customerId = :param", Order.class)
                    .setParameter("param", customer.getId());
            if (query.getResultList().size() == 0) {
                return null;
            }
            return query.getResultList();
        }
    }
}
