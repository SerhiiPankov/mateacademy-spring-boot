package mate.academy.springbootproject.repository;

import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(Map<String, String[]> params);
}
