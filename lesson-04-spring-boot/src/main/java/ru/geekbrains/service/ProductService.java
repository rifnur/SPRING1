package ru.geekbrains.service;

        import java.util.List;
        import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    List<ProductRepr> findWithFilter(String titleFilter);

    Optional<ProductRepr> findById(long id);

    void save(ProductRepr product);

    void delete(long id);
}
