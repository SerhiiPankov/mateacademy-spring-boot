package mate.academy.springbootproject.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.springbootproject.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findBookById(Long bookId);
}
