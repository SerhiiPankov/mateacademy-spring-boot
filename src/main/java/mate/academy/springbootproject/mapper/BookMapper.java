package mate.academy.springbootproject.mapper;

import mate.academy.springbootproject.config.MapperConfig;
import mate.academy.springbootproject.dto.book.BookDto;
import mate.academy.springbootproject.dto.book.CreateBookRequestDto;
import mate.academy.springbootproject.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toModel(CreateBookRequestDto dto);
}
