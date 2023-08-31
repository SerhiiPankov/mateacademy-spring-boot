package mate.academy.springbootproject.repository.specification.book.specification;

import java.util.Arrays;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String COLUMN_NAME = "author";

    @Override
    public String getKey() {
        return COLUMN_NAME;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(COLUMN_NAME)
                .in(Arrays.stream(params).toList());
    }
}
