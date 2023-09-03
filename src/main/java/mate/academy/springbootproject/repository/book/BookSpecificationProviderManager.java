package mate.academy.springbootproject.repository.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.exception.BadRequestException;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.repository.SpecificationProvider;
import mate.academy.springbootproject.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new BadRequestException(
                                "Can't find correct specification provider for key " + key));
    }
}
