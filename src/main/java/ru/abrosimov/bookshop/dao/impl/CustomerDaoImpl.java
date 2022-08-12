package ru.abrosimov.bookshop.dao.impl;

import ru.abrosimov.bookshop.dao.CustomerDao;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void create(Customer customer) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(customer);
            tx1.commit();
        }
    }

    @Override
    public void update(Customer customer) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(customer);
            tx1.commit();
        }
    }

    @Override
    public void delete(Customer customer) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(customer);
            tx1.commit();
        }
    }

    @Override
    public Customer readByID(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Customer.class, id);
        }
    }

    @Override
    public Customer readByLogin(String login) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery("FROM Customer WHERE login = :param", Customer.class)
                    .setParameter("param", login);
            if (query.getResultList().size() == 0) {
                return null;
            }
            return query.getResultList().get(0);
        }
    }
    @Override
    public List<Customer> readCustomers() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
            return query.getResultList();
        }
    }
}
