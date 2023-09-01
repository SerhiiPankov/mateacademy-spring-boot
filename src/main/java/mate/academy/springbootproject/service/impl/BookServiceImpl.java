package mate.academy.springbootproject.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.BookDto;
import mate.academy.springbootproject.dto.CreateBookRequestDto;
import mate.academy.springbootproject.exception.EntityNotFoundException;
import mate.academy.springbootproject.mapper.BookMapper;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.repository.specification.book.BookRepository;
import mate.academy.springbootproject.repository.specification.book.BookSpecificationBuilder;
import mate.academy.springbootproject.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return bookMapper.toDto(bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't get book with id: " + bookId)));
    }

    @Override
    public BookDto updateBook(Long bookId, CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);
        book.setId(bookId);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Page<BookDto> search(Pageable pageable, Map<String, String[]> params) {
        return bookRepository.findAll(bookSpecificationBuilder.build(params), pageable)
                .map(bookMapper::toDto);
    }
}
