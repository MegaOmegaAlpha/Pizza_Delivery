package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import java.sql.Time;

public class OrderDelivery {

    private long id;
    private Time timeStart;
    private Time timeFinish;
    private String details;
    private OrderDTO order;
    private CourierDTO courier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Time timeFinish) {
        this.timeFinish = timeFinish;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }
}
