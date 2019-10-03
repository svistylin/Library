package controller;

import model.Book;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;
import service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/cabinet")
    List<Book> cabinet() {
        return bookService.getAll();
    }

    @PostMapping("/error")
    String error() {
        return "incorrect data please try again";
    }

    @PutMapping("/admin/add_book")
    List<Book> addBook(@RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String genre,
                       @RequestParam int yearOfRelease,
                       @RequestParam int count) {
        bookService.addBook(new Book(name, author, genre, yearOfRelease, count));
        return bookService.getAll();
    }

    @PutMapping("/registration")
    String registration(@RequestParam String name,
                        @RequestParam String surname,
                        @RequestParam String password,
                        @RequestParam String email) {
        String message = "";
        password = passwordEncoder.encode(password);
        Optional<User> userByEmail = userService.getUserByEmail(email);
        if (userByEmail.isPresent()) {
            message = "this email is busy please try again";
        } else {
            userService.registerUser(new User(name, surname, password, email));
            message = "success";
        }
        return message;
    }
}
