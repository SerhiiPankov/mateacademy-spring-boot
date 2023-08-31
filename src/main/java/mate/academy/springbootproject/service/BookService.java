package mate.academy.springbootproject.service;

import java.util.List;
import mate.academy.springbootproject.dto.BookDto;
import mate.academy.springbootproject.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();

    BookDto getBookById(Long bookId);
}
