package ru.abrosimov.bookshop.utils;

import ru.abrosimov.bookshop.models.Book;
import ru.abrosimov.bookshop.models.BookOrder;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(BookOrder.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return sessionFactory;
    }
}
