package mate.academy.springbootproject.repository.specification.book;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.repository.specification.SpecificationBuilder;
import mate.academy.springbootproject.repository.specification.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(Map<String, String[]> params) {
        Specification<Book> spec = Specification.where(null);
        for (String key: params.keySet()) {
            if (params.get(key).length > 0) {
                spec = spec.and(
                        bookSpecificationProviderManager.getSpecificationProvider(key)
                                .getSpecification(params.get(key)));
            }
        }
        return spec;
    }
}
