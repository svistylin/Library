package service.impl;

import dao.BookDao;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public void addBook(Book book) {
        Optional<Book> checkBook = bookDao.check(book);
        if (!checkBook.isPresent()) {
            bookDao.addBook(book);
        } else {
            Book resultBook = checkBook.get();
            resultBook.setCount(resultBook.getCount() + book.getCount());
            bookDao.update(resultBook);
        }
    }

    @Override
    @Transactional
    public Optional<Book> check(Book book) {
        return bookDao.check(book);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @Transactional
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(bookDao.findById(id));
    }

    @Override
    @Transactional
    public List<Book> filter(String name, String author, String genre, int year) {
        return bookDao.filter(name, author, genre, year);
    }
}
