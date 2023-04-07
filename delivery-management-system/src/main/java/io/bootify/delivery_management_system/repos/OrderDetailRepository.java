package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
