package ru.geekbrains.salary;

        import ru.geekbrains.persist.Role;
        import ru.geekbrains.salary.Product;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Table(name = "сustomers")
@NamedQueries({
        @NamedQuery(name = "сustomerByName", query = "from Сustomer c where c.name =:сustomer"),
        @NamedQuery(name = "allСustomers", query = "from Сustomer")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String name;



    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToMany
    private List<Transaction> transactions;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Сustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
