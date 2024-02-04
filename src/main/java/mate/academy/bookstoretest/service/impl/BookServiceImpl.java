package mate.academy.bookstoretest.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstoretest.dto.BookDto;
import mate.academy.bookstoretest.dto.CreateBookRequestDto;
import mate.academy.bookstoretest.exception.EntityNotFoundException;
import mate.academy.bookstoretest.mapper.BookMapper;
import mate.academy.bookstoretest.model.Book;
import mate.academy.bookstoretest.repository.BookRepository;
import mate.academy.bookstoretest.service.BookService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(requestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> getAllByAuthor(String author) {
        return bookRepository.findAllByAuthor(author).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.update(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
