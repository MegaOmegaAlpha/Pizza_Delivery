package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Courier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends UserRepository {

    @Query(
            nativeQuery = true,
            value = "select* from user_t u " +
                    "where u.dtype = 'Courier' " +
            "        order by (select x.num from (select count(od.id) as num, u.login as name " +
            "                       from user_t u left join order_delivery od on u.id = od.courier_id " +
            "                       where u.dtype = 'Courier' and od.time_finish is null" +
            "                       group by u.login) x)",
            countQuery = "select count(*) from user_t u  " +
                    "where u.dtype = 'Courier' " +
                    "order by (select x.num from (select count(od.id) as num, u.login as name " +
                    "                       from user_t u left join order_delivery od on u.id = od.courier_id " +
                    "                       where u.dtype = 'Courier' and od.time_finish is null" +
                    "                       group by u.login) x)"
    )
    List<Courier> findMostFree();

}
