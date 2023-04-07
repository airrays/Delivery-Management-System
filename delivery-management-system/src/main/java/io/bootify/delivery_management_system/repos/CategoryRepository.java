package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
