package ru.geekbrains.service;

        import org.springframework.data.domain.Page;

        import java.util.List;
        import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Page<ProductRepr> findWithFilter(String titleFilter, Integer minPrice, Integer maxPrice,
                                     Integer page,Integer size, String sortField);

    Optional<ProductRepr> findById(long id);

    void save(ProductRepr product);

    void delete(long id);
}
