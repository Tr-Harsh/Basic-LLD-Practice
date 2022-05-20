package com.Groww.models;

public class Product {
    private Integer productId;
    private String name;
    private Coin price;

    public Product() {
    }

    public Product(Integer productId, String name, String price) {

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coin getPrice() {
        return price;
    }

    public void setPrice(Coin price) {
        this.price = price;
    }
}
