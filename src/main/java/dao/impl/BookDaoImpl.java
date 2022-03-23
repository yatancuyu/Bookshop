package dao.impl;

import dao.BookDao;
import models.Book;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public void create(Book book) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(book);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("BookCreate Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public void update(Book book) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(book);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("BookUpdate Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public void delete(Book book) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(book);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("BookDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Book readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Book> query = session.createQuery("FROM Book WHERE id = :param", Book.class)
                .setParameter("param", id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Book> readListByName(String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Book> query = session.createQuery("FROM Book WHERE name = :param", Book.class)
                .setParameter("param", title);
        return query.getResultList();
    }

    @Override
    public List<Book> readListByGenre(String genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Book> query = session.createQuery("FROM Book WHERE genre = :param", Book.class)
                .setParameter("param", genre);
        return query.getResultList();
    }

    @Override
    public int bookAmount(Book book) {
        return book.getAmount();
    }

    @Override
    public double bookPrice(Book book) {
        return book.getPrice();
    }
}
