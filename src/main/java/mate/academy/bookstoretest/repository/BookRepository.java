package mate.academy.bookstoretest.repository;

import java.util.List;
import mate.academy.bookstoretest.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
