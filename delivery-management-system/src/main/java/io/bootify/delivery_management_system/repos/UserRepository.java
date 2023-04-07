package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
