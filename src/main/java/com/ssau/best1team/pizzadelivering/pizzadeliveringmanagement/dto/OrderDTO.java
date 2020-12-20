package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private long id;
    private Date orderDate;
    private Time orderTime;
    private Time lastStatusUpdateTime;
    private int totalPrice;
    private String commentary;
    private ChosenPaymentMethodDTO chosenPaymentMethod;
    private AddressDTO address;
    private OrderStatusDTO orderStatus;
    private UserDTO user;
    private OrderDeliveryDTO orderDelivery;
    private List<PizzaOrderDTO> pizzaOrderList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ChosenPaymentMethodDTO getChosenPaymentMethod() {
        return chosenPaymentMethod;
    }

    public void setChosenPaymentMethod(ChosenPaymentMethodDTO chosenPaymentMethod) {
        this.chosenPaymentMethod = chosenPaymentMethod;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public OrderStatusDTO getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusDTO orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PizzaOrderDTO> getPizzaOrderList() {
        return pizzaOrderList;
    }

    public void setPizzaOrderList(List<PizzaOrderDTO> pizzaOrderList) {
        this.pizzaOrderList = pizzaOrderList;
    }

    public OrderDeliveryDTO getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(OrderDeliveryDTO orderDelivery) {
        this.orderDelivery = orderDelivery;
    }
}
