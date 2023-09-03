package mate.academy.springbootproject.service;

import java.util.List;
import java.util.Map;
import mate.academy.springbootproject.dto.book.BookDto;
import mate.academy.springbootproject.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long bookId);

    BookDto updateBook(Long bookId, CreateBookRequestDto bookRequestDto);

    void deleteBook(Long bookId);

    Page<BookDto> search(Pageable pageable, Map<String, String[]> params);
}
