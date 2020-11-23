package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "order_t")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_time")
    private Time orderTime;

    @Column(name = "time_of_last_status_change")
    private Time lastStatusUpdateTime;

    @Column(name = "total_price")
    private int totalPrice;

    @Column
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "chosen_payment_method_id", nullable = false)
    private ChosenPaymentMethod chosenPaymentMethod;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<PizzaOrder> pizzaOrderList;

    @OneToOne
    private OrderDelivery orderDelivery;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public Time getLastStatusUpdateTime() {
        return lastStatusUpdateTime;
    }

    public void setLastStatusUpdateTime(Time lastStatusUpdateTime) {
        this.lastStatusUpdateTime = lastStatusUpdateTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public ChosenPaymentMethod getChosenPaymentMethod() {
        return chosenPaymentMethod;
    }

    public void setChosenPaymentMethod(ChosenPaymentMethod chosenPaymentMethod) {
        this.chosenPaymentMethod = chosenPaymentMethod;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<PizzaOrder> getProductOrderList() {
        return pizzaOrderList;
    }

    public void setProductOrderList(List<PizzaOrder> pizzaOrderList) {
        this.pizzaOrderList = pizzaOrderList;
    }
}
