package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Dish;
import io.bootify.delivery_management_system.dto.DishDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query("Select Count(*) from Dish d where d.categoryId=?1")
    int dishCatCtn(Long id);

    @Transactional
    @Query(value = "Select d from Dish d join DishFlavor df where d.id=:id")
    Optional<?> getByIdWithFlavor(@Param("id") Long id);

//    List<Object[]> findByDish_Id(Long id);
}
