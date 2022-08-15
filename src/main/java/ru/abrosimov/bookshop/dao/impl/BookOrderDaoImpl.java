package ru.abrosimov.bookshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.abrosimov.bookshop.dao.BookOrderDAO;
import ru.abrosimov.bookshop.dao.GenericDAO;
import ru.abrosimov.bookshop.models.BookOrderEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Repository
public class BookOrderDaoImpl extends GenericDaoImpl<BookOrderEntity>
    implements BookOrderDAO {
}
