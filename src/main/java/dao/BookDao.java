package dao;

import model.Book;
import java.util.List;
import java.util.Optional;

public interface BookDao {

    void addBook(Book book);

    Optional<Book> check(Book book);

    void update(Book book);

    List<Book> getAll();

    Book findById(int id);

    List<Book> filter(String name, String author,String genre, int year);
}
