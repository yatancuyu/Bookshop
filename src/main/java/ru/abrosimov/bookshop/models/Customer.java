package ru.abrosimov.bookshop.models;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "customers")
public class Customer implements GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле Имя должно быть заполнено.")
    private String firstName;

    @NotEmpty(message = "Поле Отчество должно быть заполнено.")
    private String middleName;

    @NotEmpty(message = "Поле Фамилия должно быть заполнено.")
    private String lastName;

    @NotEmpty(message = "Поле Email должно быть заполнено.")
    @Email
    private String email;

    @NotEmpty(message = "Поле Номер телефона должно быть заполнено.")
    private String phoneNumber;

    @NotEmpty(message = "Поле Адрес должно быть заполнено.")
    private String address;

    @NotEmpty(message = "Поле Login должно быть заполнено.")
    private String login;

    @NotEmpty(message = "Поле password должно быть заполнено.")
    private String password;
    private boolean adminRights;

}
