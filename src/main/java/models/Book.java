package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String authors;
    private String genre;
    private String publisher;
    private int yearOfPublication;
    private int numberOfPages;
    private String pathToCover;
    private int amount;
    private double price;

    @OneToMany(mappedBy = "book")
    private Set<BookOrder> orders = new HashSet<BookOrder>();

    public Book () {}

    public Book(String name, String authors, String genre, String publisher, int yearOfPublication, int numberOfPages,
                String pathToCover, int amount, double price) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
        this.pathToCover = pathToCover;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() { return amount; }

    public Set<BookOrder> getOrders() { return orders; }

    public void setAmount(int amount) { this.amount = amount; }

    public String getPathToCover() { return pathToCover; }

    public void setPathToCover(String pathToCover) { this.pathToCover = pathToCover; }
}
