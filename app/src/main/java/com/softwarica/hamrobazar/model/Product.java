package com.softwarica.hamrobazar.model;

public class Product {

    private String name;
    private String price;
    private String condition;
    private String image;

    public Product(String name, String price, String condition, String image) {
        this.name = name;
        this.price = price;
        this.condition = condition;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
