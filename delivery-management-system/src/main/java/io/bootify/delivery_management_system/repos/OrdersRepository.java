package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
