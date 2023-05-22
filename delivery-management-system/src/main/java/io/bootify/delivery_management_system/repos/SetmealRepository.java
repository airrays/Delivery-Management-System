package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Setmeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SetmealRepository extends JpaRepository<Setmeal, Long> {
    @Query("Select Count(*) from Setmeal d where d.categoryId=?1")
    int setMealCatCtn(Long id);
}
