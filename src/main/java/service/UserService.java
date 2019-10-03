package service;

import model.Book;
import model.User;

import java.util.Optional;

public interface UserService {

    void registerUser(User user);

    void borrowBook(User user, Book book);

    Optional<User> getUserByEmail(String email);

    void returnBook(User user, Book book);
}
