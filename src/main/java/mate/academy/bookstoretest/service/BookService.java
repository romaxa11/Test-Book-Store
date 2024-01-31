package mate.academy.bookstoretest.service;

import java.util.List;
import mate.academy.bookstoretest.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
