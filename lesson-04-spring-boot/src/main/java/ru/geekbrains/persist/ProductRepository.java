package ru.geekbrains.persist;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByTitleLike(String title);

    @Query("select p from Product p where p.title like :title")
    List<Product> someQuery(@Param("title") String title);

}
