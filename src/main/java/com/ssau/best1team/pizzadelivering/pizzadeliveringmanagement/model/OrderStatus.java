package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    public static final String PROCESSED = "Заказ оформлен";
    public static final String COOKING = "Готовится";
    public static final String TAKEN_BY_COURIER = "Передан курьеру";
    public static final String DELIVERED = "Доставлено";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orderList;

    public OrderStatus() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
