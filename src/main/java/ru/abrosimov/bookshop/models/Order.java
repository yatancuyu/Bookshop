package ru.abrosimov.bookshop.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class Order implements GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private Double price;
    private String deliveryAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datetimeOfDelivery;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<BookOrderEntity> books = new HashSet<>();

    public enum Status {
        OPEN,
        CLOSED
    }

    public Order(Customer customer, double price) {
        this.customer = customer;
        this.price = price;
        this.status = Status.OPEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.price, price) == 0 &&
                customer.getId() == order.customer.getId() &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(datetimeOfDelivery, order.datetimeOfDelivery) &&
                status == order.status;
    }
}
