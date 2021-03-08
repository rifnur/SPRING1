package ru.geekbrains.service;

import ru.geekbrains.persist.Product;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

// DTO
public class ProductRepr {

    private Long id;

    @NotEmpty
    private String title;

//    @NotEmpty
    private BigDecimal price;

    public ProductRepr() {
    }

    public ProductRepr(String title) {
        this.title = title;
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
