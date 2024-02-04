package mate.academy.bookstoretest.mapper;

import mate.academy.bookstoretest.config.MapperConfig;
import mate.academy.bookstoretest.dto.BookDto;
import mate.academy.bookstoretest.dto.CreateBookRequestDto;
import mate.academy.bookstoretest.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
