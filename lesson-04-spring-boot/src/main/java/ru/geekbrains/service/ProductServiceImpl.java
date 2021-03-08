package ru.geekbrains.service;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.jpa.domain.Specification;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import ru.geekbrains.persist.*;

        import java.math.BigDecimal;
        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findWithFilter(Optional<String> titleFilter,
                                        Optional<BigDecimal> minPrice,
                                        Optional<BigDecimal> maxPrice,
                                        Optional<Integer> page,
                                        Optional<Integer> size,
                                        Optional<String> sortField,
                                        Optional<String> sortOrder) {
        Specification<Product> spec = Specification.where(null);
        if (titleFilter.isPresent() && !titleFilter.get().isBlank()) {
            logger.info("Adding {} to filter", titleFilter.get());
            spec = spec.and(ProductSpecification.titleLike(titleFilter.get()));
        }
        if (minPrice.isPresent()) {
            logger.info("Adding {} to filter", minPrice.get());
            spec = spec.and(ProductSpecification.minPriceFilter(minPrice.get()));
        }
        if (maxPrice.isPresent()) {
            logger.info("Adding {} to filter", maxPrice.get());
            spec = spec.and(ProductSpecification.maxPriceFilter(maxPrice.get()));
        }
        if (sortField.isPresent() && !sortField.isPresent()) {
            return productRepository.findAll(spec, PageRequest.of(page.orElse(1) - 1, size.orElse(5),
                    Sort.by(Sort.Direction.fromString(sortOrder.orElse("ASC")), sortField.get()))
                    );
        }
        return productRepository.findAll(spec, PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
