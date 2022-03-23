package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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
    private String datetimeOfDelivery;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(mappedBy = "order")
    private Set<BookOrder> books = new HashSet<BookOrder>();

    public Order() {}

    public Order(Customer customer, double price, String deliveryAddress, String datetimeOfDelivery, Status status) {
        this.customer = customer;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
        this.datetimeOfDelivery = datetimeOfDelivery;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<BookOrder> getBooks() { return books; }

    public void setBooks(Set<BookOrder> books) { this.books = books;}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDatetimeOfDelivery() {
        return datetimeOfDelivery;
    }

    public void setDatetimeOfDelivery(String datetimeOfDelivery) {
        this.datetimeOfDelivery = datetimeOfDelivery;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        OPEN,
        PROCESSING,
        INTRANSIT,
        DELIVERED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", price=" + price +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", datetimeOfDelivery='" + datetimeOfDelivery + '\'' +
                ", status=" + status +
                ", books=" + books +
                '}';
    }
}
