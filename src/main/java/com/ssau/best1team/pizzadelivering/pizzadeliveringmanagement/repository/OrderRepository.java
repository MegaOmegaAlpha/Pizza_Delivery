package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Long, Order> {
}
