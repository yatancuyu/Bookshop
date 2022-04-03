package dao.impl;

import dao.BookDao;
import models.Book;
import models.BookOrder;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class BookDaoImpl implements BookDao {

    @Override
    public void create(Book book) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(book);
            tx1.commit();
        }
    }

    @Override
    public void update(Book book) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(book);
            tx1.commit();
        }
    }

    @Override
    public void delete(Book book) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(book);
            tx1.commit();
        }
    }

    @Override
    public Book readByID(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    @Override
    public List<Book> readListByName(String title) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE name = :param", Book.class)
                    .setParameter("param", title);
            return query.getResultList();
        }
    }

    @Override
    public List<Book> readListByGenre(String genre) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE genre = :param", Book.class)
                    .setParameter("param", genre);
            return query.getResultList();
        }
    }
}
