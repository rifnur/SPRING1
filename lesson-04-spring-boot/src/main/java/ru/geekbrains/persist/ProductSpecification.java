package ru.geekbrains.persist;

        import org.springframework.data.jpa.domain.Specification;

        import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> titleLike(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> minPriceFilter(BigDecimal minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPriceFilter(BigDecimal maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
