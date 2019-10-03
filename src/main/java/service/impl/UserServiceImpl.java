package service.impl;

import dao.BookDao;
import dao.UserDao;
import model.Book;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BookDao bookDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, BookDao bookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void registerUser(User user) {
        User userByEmail = userDao.getUserByEmail(user.getEmail());
        if (userByEmail == null) {
            userDao.registerUser(user);
        }
    }

    @Override
    @Transactional
    public void borrowBook(User user, Book book) {
        List<Book> rentedBooks;
        Optional<Book> check = bookDao.check(book);
        Book book1 = check.get();
        book1.setCount(book.getCount() - 1);
        bookDao.update(book1);
        if (user.getRentedBooks() != null) {
            rentedBooks = user.getRentedBooks();
        } else {
            rentedBooks = new ArrayList<>();
        }
        rentedBooks.add(book);
        user.setRentedBooks(rentedBooks);
        userDao.update(user);
    }

    @Override
    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userDao.getUserByEmail(email));
    }

    @Override
    @Transactional
    public void returnBook(User user, Book book) {
        List<Book> rentedBooks = user.getRentedBooks();
        for (Book rentedBook : rentedBooks) {
            if (book.equals(rentedBook)) {
                book = rentedBook;
                break;
            }
        }
        rentedBooks.remove(book);
        user.setRentedBooks(rentedBooks);
        userDao.update(user);
        Optional<Book> check = bookDao.check(book);
        if (check.isPresent()) {
            Book checkedBook = check.get();
            checkedBook.setCount(checkedBook.getCount() + 1);
            bookDao.update(checkedBook);
        }
    }
}