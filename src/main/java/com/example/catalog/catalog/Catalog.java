package com.example.catalog.catalog;

import javax.persistence.*;

@Entity
@Table
public class Catalog {
    @Id
    @SequenceGenerator(
            name="catalog_seq",
            sequenceName = "catalog_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "catalog_seq"
    )
    private Long id;
    private int Quantity;
    private int Price;
    private String Title;
    private String Subject;

    public Catalog(int quantity, int price, String title, String subject) {
        Quantity = quantity;
        Price = price;
        Title = title;
        Subject = subject;
    }

    public Catalog() {

    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}

