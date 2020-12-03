package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;


public class PizzaOrderDTO {

    private long id;
    private int amount;
    private String doughType;
    private String pizzaSize;
    private PizzaDTO pizza;
    private OrderDTO order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public PizzaDTO getPizza() {
        return pizza;
    }

    public void setPizza(PizzaDTO pizza) {
        this.pizza = pizza;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}
