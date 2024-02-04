package mate.academy.bookstoretest.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstoretest.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findBookById(Long id);

    List<Book> findAllByAuthor(String author);

    Book update(Book book);

    void deleteById(Long id);
}
