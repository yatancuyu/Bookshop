package ru.abrosimov.bookshop.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books_in_orders")
public class BookOrderEntity implements GenericEntity, Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "bookid", referencedColumnName = "id")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    private Order order;

    private int amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookOrderEntity)) return false;
        BookOrderEntity bookOrder = (BookOrderEntity) o;
        return amount == bookOrder.amount &&
                order.getId() == bookOrder.order.getId() &&
                book.getId() == bookOrder.book.getId();
    }
}
