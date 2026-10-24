package app.luxion.bookshelf;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private final JdbcClient jdbc;

    public BookRepository(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    public List<Book> findAll() {
        return jdbc.sql("select id, title, author, published_year from books order by id")
                .query(Book.class)
                .list();
    }

    public Optional<Book> findById(long id) {
        return jdbc.sql("select id, title, author, published_year from books where id = :id")
                .param("id", id)
                .query(Book.class)
                .optional();
    }

    public Book save(Book book) {
        long id = jdbc.sql("insert into books (title, author, published_year) values (:title, :author, :year) returning id")
                .param("title", book.title())
                .param("author", book.author())
                .param("year", book.publishedYear())
                .query(Long.class)
                .single();
        return new Book(id, book.title(), book.author(), book.publishedYear());
    }
}
