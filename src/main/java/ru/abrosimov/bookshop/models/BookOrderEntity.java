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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @NonNull
    @ToString.Exclude
    private Book book;


    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @NonNull
    @ToString.Exclude
    private Order order;

    private int amount;

    public BookOrderEntity(Book book, Order order, int amount) {
        this.book = book;
        this.order = order;
        this.amount = amount;
    }

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
