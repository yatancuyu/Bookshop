package ru.abrosimov.bookshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.abrosimov.bookshop.dao.BookOrderDAO;
import ru.abrosimov.bookshop.models.BookOrderEntity;

@Repository
public class BookOrderDAOImpl extends GenericDAOImpl<BookOrderEntity>
    implements BookOrderDAO {
}
