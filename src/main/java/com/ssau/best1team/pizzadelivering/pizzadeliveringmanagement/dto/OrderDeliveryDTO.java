package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import java.sql.Time;

public class OrderDeliveryDTO {

    private long id;
    private Time timeStart;
    private Time timeFinish;
    private String details;

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

}
