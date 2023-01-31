package ru.abrosimov.bookshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.abrosimov.bookshop.dao.BookDAO;
import ru.abrosimov.bookshop.models.Book;

@Repository
public class BookDAOImpl extends GenericDAOImpl<Book>
        implements BookDAO {
}
