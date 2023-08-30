package mate.academy.springbootproject.service.impl;

import java.util.List;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.repository.BookRepository;
import mate.academy.springbootproject.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
