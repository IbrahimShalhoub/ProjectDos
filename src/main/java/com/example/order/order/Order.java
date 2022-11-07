package com.example.order.order;

import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Order {
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
    private Long Idd;
    private Long id;

    private int quantity;

    public Order(Long id,int quantity) {
        this.quantity = quantity;
        this.id=id;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

