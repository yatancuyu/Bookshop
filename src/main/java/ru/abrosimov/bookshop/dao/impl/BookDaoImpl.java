package ru.abrosimov.bookshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.abrosimov.bookshop.dao.BookDAO;
import ru.abrosimov.bookshop.models.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@Repository
public class BookDaoImpl extends GenericDaoImpl<Book>
        implements BookDAO {
}
