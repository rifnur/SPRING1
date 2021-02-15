package ru.geekbrains.salary;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

    @ManyToMany
    private List<Customer> customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    public Transaction() {
    }

    public Transaction(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", customer='" + customer.getId() + '\'' +
                ", product='" + product.getId() + '\'' +
                '}';
    }
}
