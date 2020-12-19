package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(
            nativeQuery = true,
            value = "select* from address where id in (select a.address_id from customer_address a where a.customer_id = ?1)",
            countQuery = "select count(*) from address where id in (select a.address_id from customer_address a where a.customer_id = ?1)"
    )
    List<Address> findAllByUserId(long customerId);

}
