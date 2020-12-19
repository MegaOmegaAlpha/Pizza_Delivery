package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.observer;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderStatus;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.CourierOrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusChangeListener implements ChangeListener {

    private CourierOrderResource courierOrderResource;

    @Autowired
    public OrderStatusChangeListener(CourierOrderResource courierOrderResource) {
        this.courierOrderResource = courierOrderResource;
    }

    public OrderStatusChangeListener() {
    }

    @Override
    public void update(Event event) {
        try {
            switch (event.getStatus()) {
                case OrderStatus.TAKEN_BY_COURIER:
                    courierOrderResource.assignOrderToCourier(event.getOrderId());
                    break;
                case OrderStatus.DELIVERED:
                    courierOrderResource.finishOrderDelivering(event.getOrderId());
                    break;
            }
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
