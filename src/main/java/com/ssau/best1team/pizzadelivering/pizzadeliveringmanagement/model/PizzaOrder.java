package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "pizza_order")
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private int amount;

    @Column(name = "dough_type")
    private String doughType;

    @Column(name = "pizza_size")
    private String pizzaSize;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public PizzaOrder() {
    }

    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getDoughType() {
        return doughType;
    }

    public void setDoughType(String doughType) {
        this.doughType = doughType;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Pizza getProduct() {
        return pizza;
    }

    public void setProduct(Pizza pizza) {
        this.pizza = pizza;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
