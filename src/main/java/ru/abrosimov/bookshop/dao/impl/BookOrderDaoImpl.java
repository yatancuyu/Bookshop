package ru.abrosimov.bookshop.dao.impl;

import ru.abrosimov.bookshop.dao.BookOrderDao;
import ru.abrosimov.bookshop.models.BookOrder;
import ru.abrosimov.bookshop.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BookOrderDaoImpl implements BookOrderDao {
    @Override
    public void create(BookOrder bookOrder) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(bookOrder);
            tx1.commit();
        }
    }

    @Override
    public void update(BookOrder bookOrder) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(bookOrder);
            tx1.commit();
        }
    }

    @Override
    public void delete(BookOrder bookOrder) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(bookOrder);
            tx1.commit();
        }
    }

    @Override
    public BookOrder readByID(int bookId, int orderId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<BookOrder> query = session.createQuery("FROM BookOrder WHERE bookId = :param AND orderId = :param2", BookOrder.class)
                    .setParameter("param", bookId).setParameter("param2", orderId);
            if (query.getResultList().size() == 0) {
                return null;
            }
            return query.getResultList().get(0);
        }
    }
}
