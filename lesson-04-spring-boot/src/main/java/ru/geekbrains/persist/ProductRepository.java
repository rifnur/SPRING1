package ru.geekbrains.persist;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.math.BigDecimal;
        import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findProductByNameLike(String name);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

}
