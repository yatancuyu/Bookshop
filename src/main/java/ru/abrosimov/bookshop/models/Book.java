package ru.abrosimov.bookshop.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "books")
public class Book implements GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String authors;
    private String genre;
    private String publisher;
    private Integer yearOfPublication;
    private Integer numberOfPages;
    private Integer amount;
    private Double price;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Set<BookOrderEntity> orders = new HashSet<>();

    public Book(String name, String authors, String genre, String publisher,
                Integer yearOfPublication, Integer numberOfPages,
                Integer amount, Double price) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(yearOfPublication, book.yearOfPublication) &&
                Objects.equals(numberOfPages, book.numberOfPages) &&
                Objects.equals(amount, book.amount) &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(name, book.name) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(publisher, book.publisher);
    }
}
