package service;

import config.AppConfig;
import model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = AppConfig.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        Book book = new Book("asd", "qwe", "zxc", 11, 21);
        bookService.addBook(book);
        Book firstCheckRes = bookService.check(book).get();
        bookService.addBook(book);
        Book secondCheckRes = bookService.check(book).get();
        Assert.assertEquals(firstCheckRes.getCount() + book.getCount(), secondCheckRes.getCount());
    }

    @Test
    public void check() {
        Book book = new Book("ostrov", "sokrovishch", "zxc", 11, 21);
        bookService.addBook(book);
        Optional<Book> check = bookService.check(book);
        Book resultBook = check.get();
        Assert.assertEquals(book.getName(), resultBook.getName());
        Assert.assertEquals(book.getAuthor(), resultBook.getAuthor());
        Assert.assertEquals(book.getGenre(), resultBook.getGenre());
    }

    @Test()
    public void getAll() {
        List<Book> all = bookService.getAll();
        Assert.assertNotNull(all);
    }

    @Test
    public void findById() {
        Optional<Book> byId = bookService.findById(8);
        Book book = byId.get();
        Assert.assertEquals(Long.valueOf(8), book.getId());
    }

    @Test
    public void filter() {
        Book book = new Book("ostrov", "sokrovishch", "fantastic", 11, 21);
        bookService.addBook(book);
        List<Book> result = bookService.filter("ostrov", "sokrovishch", "fantastic", 0);
        Assert.assertEquals(1, result.size());
    }
}
