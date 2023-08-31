package mate.academy.springbootproject.service;

import java.util.List;
import java.util.Map;
import mate.academy.springbootproject.dto.BookDto;
import mate.academy.springbootproject.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto getBookById(Long bookId);

    BookDto updateBook(Long bookId, CreateBookRequestDto bookRequestDto);

    void deleteBook(Long bookId);

    List<BookDto> search(Map<String, String[]> params);
}
