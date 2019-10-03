package controller;

import model.Book;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;
import service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
public class UserContrller {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/user/borrow_book")
    List<Book> getm(@RequestParam int bookId, @AuthenticationPrincipal User user) {
        Optional<Book> byId = bookService.findById(bookId);
        if (byId.isPresent() && byId.get().getCount() > 0) {
            userService.borrowBook(user, byId.get());
        }
        return bookService.getAll();
    }

    @PutMapping("/user/return_book")
    List<Book> returnBook(@RequestParam int id, @AuthenticationPrincipal User user) {
        Optional<Book> byId = bookService.findById(id);
        if (byId.isPresent()) {
            Book book = byId.get();
            userService.returnBook(user, book);
        }
        return bookService.getAll();
    }

    @GetMapping("/filter")
    List<Book> filter(@RequestParam String name,
                      @RequestParam String author,
                      @RequestParam String genre,
                      @RequestParam int year) {
        return bookService.filter(name, author, genre, year);
    }
}
