package mate.academy.springbootproject.repository.book.specification;

import java.util.Arrays;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String COLUMN_NAME = "title";

    @Override
    public String getKey() {
        return COLUMN_NAME;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(COLUMN_NAME)
                .in(Arrays.stream(params).toList());
    }
}
