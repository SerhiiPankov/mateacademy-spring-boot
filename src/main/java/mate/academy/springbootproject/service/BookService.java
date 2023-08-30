package mate.academy.springbootproject.service;

import java.util.List;
import mate.academy.springbootproject.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
