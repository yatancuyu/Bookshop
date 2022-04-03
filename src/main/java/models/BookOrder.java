package models;

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
public class BookOrder implements Serializable {
    @Id
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
        if (!(o instanceof BookOrder)) return false;
        BookOrder bookOrder = (BookOrder) o;
        return amount == bookOrder.amount &&
                order.getId() == bookOrder.order.getId() &&
                book.getId() == bookOrder.book.getId();
    }
}
