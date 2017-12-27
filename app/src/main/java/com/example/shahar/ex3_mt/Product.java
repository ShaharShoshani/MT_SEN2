package com.example.shahar.ex3_mt;

/**
 * Created by eliran on 26/12/17.
 */

public class Product {

    public Product(){}

    public Product(String nameProduct, String quantity, String price) {
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String nameProduct;
    private String quantity;
    private String price;

}
