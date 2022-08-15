package ru.abrosimov.bookshop.dao.impl;

import ru.abrosimov.bookshop.dao.CustomerDAO;
import ru.abrosimov.bookshop.models.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAOImpl extends GenericDaoImpl<Customer>
        implements CustomerDAO {
}
