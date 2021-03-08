package ru.geekbrains.service;

import ru.geekbrains.persist.Product;

import java.util.Objects;

public class LineItem {

    private Product product;

    private UserRepr user;

    private Integer qty;

    public LineItem() {
    }

    public LineItem(long productId, long userId) {
        this.product = new Product();
        this.product.setId(productId);
        this.user = new UserRepr();
        this.user.setId(userId);
    }

    public LineItem(Product product, UserRepr user, Integer qty) {
        this.product = product;
        this.user = user;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return product.getId().equals(lineItem.product.getId()) && user.getId().equals(lineItem.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId(), user.getId());
    }
}
