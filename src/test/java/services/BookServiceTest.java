package services;

import models.Book;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BookServiceTest {

    @Test
    public void createBook() {
        BookService bookService = new BookService();
        Book newBook = new Book("Война и Мир", "Л. Н. Толстой", "Эпос", "Азбука",
                                1999, 2050, "../../../../docs/images/WarAndPeace.png",
                                100500, 3999.99);
        bookService.createBook(newBook);
        Book checkBook = bookService.readBooksListByName("Война и Мир").get(0);
        Assert.assertEquals(newBook, checkBook);
        bookService.deleteBook(newBook);
    }

    @Test
    public void updateBook() {
        BookService bookService = new BookService();
        Book newBook = new Book("Война и Мир", "Л. Н. Толстой", "Эпос", "Азбука",
                1999, 2050, "../../../../docs/images/WarAndPeace.png",
                100500, 3999.99);
        bookService.createBook(newBook);
        newBook.setPrice(newBook.getPrice() + 1000);
        bookService.updateBook(newBook);
        Book checkBook = bookService.readBooksListByName("Война и Мир").get(0);
        Assert.assertEquals(newBook, checkBook);
        bookService.deleteBook(newBook);
    }

    @Test
    public void readBookByID() {
        BookService bookService = new BookService();
        Book newBook = new Book("Война и Мир", "Л. Н. Толстой", "Эпос", "Азбука",
                1999, 2050, "../../../../docs/images/WarAndPeace.png",
                100500, 3999.99);
        bookService.createBook(newBook);
        Book checkBook = bookService.readBookByID(newBook.getId());
        Assert.assertEquals(newBook, checkBook);
        bookService.deleteBook(newBook);
    }

    @Test
    public void readBooksListByGenre() {
        BookService bookService = new BookService();
        List<Book> books =  List.of(new Book("Война и Мир. Том 1", "Л. Н. Толстой", "Эпос", "Азбука",
                1999, 450, "../../../../docs/images/WarAndPeace1.png",
                100500, 3999.99),
                new Book("Война и Мир. Том 2", "Л. Н. Толстой", "Эпос", "Азбука",
                        1999, 500, "../../../../docs/images/WarAndPeace2.png",
                        100500, 3999.99),
                new Book("Война и Мир. Том 3", "Л. Н. Толстой", "Эпос", "Азбука",
                        1999, 600, "../../../../docs/images/WarAndPeace3.png",
                        100500, 3999.99),
                new Book("Анна Каренина", "Л. Н. Толстой", "Драма", "Азбука",
                        1989, 2050, "../../../../docs/images/AnnaKarenina.png",
                        10, 3899.99));
        for (Book book : books) {
            bookService.createBook(book);
        }
        List<Book> checkBooks = bookService.readBooksListByGenre("Эпос");
        Assert.assertEquals(books.size() - 1, checkBooks.size());
        Assert.assertTrue(checkBooks.contains(books.get(0)));
        Assert.assertTrue(checkBooks.contains(books.get(1)));
        Assert.assertTrue(checkBooks.contains(books.get(2)));
        Assert.assertFalse(checkBooks.contains(books.get(3)));
        for (Book book : books) {
            bookService.deleteBook(book);
        }
    }
}