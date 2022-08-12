package ru.abrosimov.bookshop.services;

import ru.abrosimov.bookshop.models.Book;
import ru.abrosimov.bookshop.models.BookOrder;
import ru.abrosimov.bookshop.models.Customer;
import ru.abrosimov.bookshop.models.Order;
import org.junit.Assert;
import org.junit.Test;

public class BookOrderServiceTest {

    @Test
    public void createAndUpdateBookOrder() {
        OrderService orderService = new OrderService();
        CustomerService customerService = new CustomerService();
        BookService bookService = new BookService();
        BookOrderService bookOrderService = new BookOrderService();
        Customer newCustomer = new Customer("Иванов Иван Иванович", "ivanov@yandex.ru", "89999991111",
                "г. Москва, ул. Ломоносовский проспект, д. 1", "ivanov", "qwerty", false);
        customerService.createCustomer(newCustomer);
        Order newOrder = new Order(newCustomer, 6569.90, null,
                null, Order.Status.OPEN);
        orderService.createOrder(newOrder);
        Book newBook = new Book("Война и Мир", "Л. Н. Толстой", "Эпос", "Азбука",
                1999, 2050, "../../../../docs/images/WarAndPeace.png",
                100500, 3999.99);
        bookService.createBook(newBook);
        BookOrder newBookOrder = new BookOrder(newBook, newOrder, 1);
        bookOrderService.createBookOrder(newBookOrder);
        BookOrder check = bookOrderService.readByID(newBook.getId(), newOrder.getId());
        Assert.assertEquals(newBookOrder, check);

        newBookOrder.setAmount(2);
        bookOrderService.updateBookOrder(newBookOrder);
        check = bookOrderService.readByID(newBook.getId(), newOrder.getId());
        Assert.assertEquals(newBookOrder, check);

        bookOrderService.deleteBookOrder(newBookOrder);
        bookService.deleteBook(newBook);
        orderService.deleteOrder(newOrder);
        customerService.deleteCustomer(newCustomer);
        Assert.assertNull(bookOrderService.readByID(newBook.getId(), newOrder.getId()));
    }
}