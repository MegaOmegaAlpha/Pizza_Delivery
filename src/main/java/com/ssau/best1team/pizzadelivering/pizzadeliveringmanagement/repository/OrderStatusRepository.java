package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<Long, OrderStatus> {
}
