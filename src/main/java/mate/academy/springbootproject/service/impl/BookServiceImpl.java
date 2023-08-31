package mate.academy.springbootproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.BookDto;
import mate.academy.springbootproject.dto.CreateBookRequestDto;
import mate.academy.springbootproject.exception.EntityNotFoundException;
import mate.academy.springbootproject.mapper.BookMapper;
import mate.academy.springbootproject.repository.BookRepository;
import mate.academy.springbootproject.service.BookService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return bookMapper.toDto(bookRepository.findBookById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't get book with id: " + bookId)));
    }
}
