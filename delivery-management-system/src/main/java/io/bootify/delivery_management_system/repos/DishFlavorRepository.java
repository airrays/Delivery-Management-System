package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.DishFlavor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishFlavorRepository extends JpaRepository<DishFlavor, Long> {
    @Transactional
//    @Query(value = "Select df from DishFlavor df where df.id= :id")
//    List<DishFlavor> findByDishId(Long id);
    List<DishFlavor> findByDish_Id(Long id);
}
