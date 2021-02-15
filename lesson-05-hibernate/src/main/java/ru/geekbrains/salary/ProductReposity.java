
package ru.geekbrains.salary;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import java.util.List;

public class ProductReposity {

    private final EntityManagerFactory emFactory;

    public ProductReposity(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> productList = em.createQuery("from Product", Product.class)
                .getResultList();
        return productList;

    }

    public Product findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        return product;
    }

    public void insert(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void updateTitle(Product product, String title) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        product.setTitle("title");
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
        em.getTransaction().commit();
        em.close();

    }

}
