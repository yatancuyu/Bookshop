package models;

import lombok.*;

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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    private double price;
    private String deliveryAddress;
    private Date datetimeOfDelivery;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<BookOrder> books = new HashSet<>();

    public enum Status {
        OPEN,
        PROCESSING,
        INTRANSIT,
        DELIVERED;
    }

    public Order(Customer customer, double price, String deliveryAddress,
                 Date datetimeOfDelivery, Status status) {
        this.customer = customer;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
        this.datetimeOfDelivery = datetimeOfDelivery;
        this.status = status;
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
