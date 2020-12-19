package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.observer;

public class Event {

    private long orderId;
    private String status;

    public Event(long orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setEventType(String eventType) {
        this.status = eventType;
    }
}
