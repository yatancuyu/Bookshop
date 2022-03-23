package dao.impl;

import dao.OrderDao;
import models.Book;
import models.Customer;
import models.Order;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    public void create(Order order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(order);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("OrderCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Order order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(order);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("OrderUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Order order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(order);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("OrderDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Order readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Order> readOrdersByCustomer(Customer customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Order> query = session.createQuery("FROM Order WHERE id = :param", Order.class)
                .setParameter("param", customer);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Order> readOrders() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Order> criteria = session.getCriteriaBuilder().createQuery(Order.class);
        criteria.from(Order.class);
        List<Order> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}
