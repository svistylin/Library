package dao.impl;

import dao.BookDao;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    SessionFactory sessionFactory;

    @Autowired
    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(book);
    }

    @Override
    public Optional<Book> check(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Book> getBook = currentSession.createQuery("from Book b where b.name =:name" +
                " and b.author =:author");
        getBook.setParameter("name", book.getName());
        getBook.setParameter("author", book.getAuthor());
        Book result = ((Query<Book>) getBook).uniqueResult();
        return Optional.ofNullable(result);
    }

    @Override
    public void update(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(book);
    }

    @Override
    public List<Book> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Book> from_book = currentSession.createQuery("from Book", Book.class);
        List<Book> list = from_book.list();
        return list;
    }

    @Override
    public Book findById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Book> getBook = currentSession.createQuery("from Book b where b.id =:id");
        getBook.setParameter("id", id);
        Book book = getBook.uniqueResult();
        return book;
    }

    @Override
    public List<Book> filter(String name, String author, String genre, int year) {
        Session currentSession = sessionFactory.getCurrentSession();
        StringBuilder stringBuilder = new StringBuilder("From Book b where ");
        int firstCondition = 0;
        if (name != "") {
            stringBuilder.append("b.name = '" + name + "'");
            firstCondition++;
        }
        if (firstCondition != 0) {
            if (name != "") {
                stringBuilder.append(" and ");
            }
            stringBuilder.append("b.author = '" + author + "'");
            firstCondition++;
        }
        if (firstCondition != 0) {
            if (name != "") {
                stringBuilder.append(" and ");
            }
            stringBuilder.append("b.genre = '" + genre + "'");
            firstCondition++;
        }
        if (year != 0) {
            if (firstCondition != 0) {
                stringBuilder.append(" and ");
            }
            stringBuilder.append("b.yearOfRelease = '" + year + "'");
        }
        String s = stringBuilder.toString();
        List<Book> result = currentSession.createQuery(s, Book.class).list();
        return result;
    }
}
