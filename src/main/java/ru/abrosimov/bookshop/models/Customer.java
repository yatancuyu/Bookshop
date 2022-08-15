package ru.abrosimov.bookshop.models;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer implements GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String login;
    private String password;
    private boolean adminRights;

    public Customer(String fullName, String email, String phoneNumber, String address,
                    String login, String password, boolean adminRights) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.login = login;
        this.password = password;
        this.adminRights = adminRights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return adminRights == customer.adminRights &&
                Objects.equals(fullName, customer.fullName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(login, customer.login) &&
                Objects.equals(password, customer.password);
    }

}
