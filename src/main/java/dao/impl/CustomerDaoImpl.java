package dao.impl;

import dao.CustomerDao;
import models.Customer;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    public void create(Customer customer) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(customer);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("CustomerCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Customer customer) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(customer);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("CustomersUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Customer customer) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(customer);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("CustomerDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Customer readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE id = :param", Customer.class)
                .setParameter("param", id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public Customer readByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE login = :param", Customer.class)
                .setParameter("param", login);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }
    @Override
    public List<Customer> readCustomers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
        return query.getResultList();
    }
}
