package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Long, Role> {
}
