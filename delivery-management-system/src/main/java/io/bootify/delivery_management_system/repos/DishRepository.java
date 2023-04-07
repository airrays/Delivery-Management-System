package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepository extends JpaRepository<Dish, Long> {
}
