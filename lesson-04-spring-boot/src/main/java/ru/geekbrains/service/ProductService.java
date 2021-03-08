package ru.geekbrains.service;

        import org.springframework.data.domain.Page;
        import org.springframework.data.jpa.domain.Specification;
        import ru.geekbrains.persist.Product;

        import java.math.BigDecimal;
        import java.util.List;
        import java.util.Optional;

public interface ProductService {
    Page<Product> findWithFilter(Optional<String> titleFilter,
                                     Optional<BigDecimal> minPrice,
                                     Optional<BigDecimal> maxPrice,
                                     Optional<Integer> page,
                                     Optional<Integer> size,
                                     Optional<String> sortField,
                                     Optional<String> sortOrder);
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(long id);
}
