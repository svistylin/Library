package service;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void addBook(Book book);

    Optional<Book> check(Book book);

    List<Book> getAll();

    Optional<Book> findById(int id);

    List<Book> filter(String name, String author, String genre, int year);
}
