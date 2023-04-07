package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
