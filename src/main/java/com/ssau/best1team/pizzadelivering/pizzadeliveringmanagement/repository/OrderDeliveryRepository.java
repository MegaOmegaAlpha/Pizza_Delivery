package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

    @Query(
            nativeQuery = true,
            value = "select* from order_delivery where courier_id = ?1",
            countQuery = "select count(*) from order_delivery where courier_id = ?1"
    )
    List<OrderDelivery> getAllByCourierId(long courierId);

}
