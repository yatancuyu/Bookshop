package models;

import javax.persistence.*;

@Entity
@Table(name = "books_in_orders")
public class BookOrder {
    @Id
    @ManyToOne
    @JoinColumn(name = "bookid", referencedColumnName = "id")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    private Order order;

    private int amount;
}
