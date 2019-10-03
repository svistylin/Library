package service;

import config.AppConfig;
import config.SecurityConfig;
import model.Book;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AppConfig.class, SecurityConfig.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Test
    public void registerUser() {
        User vasya = new User("vasya", "pupkin", "12345", "vasya@gmail.com");
        userService.registerUser(vasya);
        User user = userService.getUserByEmail(vasya.getEmail()).get();
        Assert.assertEquals(user.getEmail(), vasya.getEmail());
    }

    @Test
    public void borrowBook() {
        User vasya = new User("vasya", "pupkin", "12345", "vasya@gmail.com");
        Book book = new Book("ostrov", "sokrovishch", "fantastic", 11, 21);
        userService.registerUser(vasya);
        User vasyaInDB = userService.getUserByEmail(vasya.getEmail()).get();
        bookService.addBook(book);
        Book resultBook = bookService.check(book).get();
        userService.borrowBook(vasyaInDB, resultBook);
        User user = userService.getUserByEmail(vasya.getEmail()).get();
        Assert.assertEquals(true, resultBook.equals(user.getRentedBooks().get(0)));
    }

    @Test
    public void getUserByEmail() {
        User vasya = new User("vasya", "pupkin", "12345", "vasya@gmail.com");
        userService.registerUser(vasya);
        User userByEmail = userService.getUserByEmail(vasya.getEmail()).get();
        Assert.assertEquals(userByEmail.getEmail(), vasya.getEmail());
    }

    @Test
    public void returnBook() {
        User vasya = new User("vasya", "pupkin", "12345", "vasya@gmail.com");
        Book book = new Book("ostrov", "sokrovishch", "fantastic", 11, 21);
        userService.registerUser(vasya);
        User vasyaInDB = userService.getUserByEmail(vasya.getEmail()).get();
        bookService.addBook(book);
        Book resultBook = bookService.check(book).get();
        userService.borrowBook(vasyaInDB, resultBook);
        User user = userService.getUserByEmail(vasya.getEmail()).get();
        userService.returnBook(user, book);
        List<Book> rentedBooks = userService.getUserByEmail(user.getEmail()).get().getRentedBooks();
        Assert.assertEquals(rentedBooks, null);
    }
}
