package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.DishFlavor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishFlavorRepository extends JpaRepository<DishFlavor, Long> {
}
