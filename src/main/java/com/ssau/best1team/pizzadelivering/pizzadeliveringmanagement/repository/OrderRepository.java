package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            nativeQuery = true,
            value = "select* from order_t where order_status_id = 1?",
            countQuery = "select count(*) from order_t where order_status_id = 1?"
    )
    List<Order> findOrdersIgnoringSpecificOrderStatus(long statusToIgnore);

    List<Order> findAllByCustomerId(long customerId);

    @Query(
            nativeQuery = true,
            value = "select* from order_t where customer_id = ?1 and id = ?2",
            countQuery = "select count(*) from order_t where customer_id = ?1 and id = ?2"
    )
    Order findByCustomerId(long customerId, long orderId);

    @Query(
            nativeQuery = true,
            value = "select* from order_t o " +
                    "where o.id in (select oo.id" +
                    "       from order_t oo join order_delivery od on oo.id = od.order_id " +
                    "       where od.courier_id = ?1)",
            countQuery = "select count(*) from order_t o " +
                    "where o.id in (select oo.id" +
                    "       from order_t oo join order_delivery od on oo.id = od.order_id " +
                    "       where od.courier_id = ?1)"
    )
    List<Order> findOrderForCourier(long courierId);

}
