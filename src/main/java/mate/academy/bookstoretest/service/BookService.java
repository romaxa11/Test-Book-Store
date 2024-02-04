package mate.academy.bookstoretest.service;

import java.util.List;
import mate.academy.bookstoretest.dto.BookDto;
import mate.academy.bookstoretest.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);
}
