package ru.abrosimov.bookshop.dao.impl;

import ru.abrosimov.bookshop.dao.OrderDAO;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAOImpl extends GenericDaoImpl<Order>
        implements OrderDAO {
}
