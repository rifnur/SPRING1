package ru.geekbrains.salary;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import java.util.List;
        import java.util.function.Consumer;
        import java.util.function.Function;

public class CustomerRepository {

    private final EntityManagerFactory emFactory;

    public CustomerRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Customer> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("allCustomers", Customer.class).getResultList()
        );
    }

    public Customer findById(long id) {
        return executeForEntityManager(
                em -> em.find(Customer.class, id)
        );
    }

    public void insert(Customer customer) {
        executeInTransaction(em -> em.persist(customer));
    }

    public void update(Customer customer) {
        executeInTransaction(em -> em.merge(customer));
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
