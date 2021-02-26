package ru.geekbrains.persist;

        import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {

    public static Specification<Product> titleLike(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> minPrice(Integer minPrice) {
        return (root, query, cb) -> cb.ge(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(Integer maxPrice) {
        return (root, query, cb) -> cb.le(root.get("price"), maxPrice);
    }
}
