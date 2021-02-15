package ru.geekbrains.salary;

import ru.geekbrains.persist.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "productByTitle", query = "from Product p where p.title=:title"),
        @NamedQuery(name = "allProducts", query = "from Product")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String title;

    @Column( nullable = false)
    private int price;


//    @ManyToOne(optional = false)
//    @JoinColumn(name = "customer_id")
////    private Customer customer;
//    private List<Customer> customers;
@ManyToMany(mappedBy = "roles")
private List<Customer> customers;

    public Product() {
    }



    public Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public Product(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
