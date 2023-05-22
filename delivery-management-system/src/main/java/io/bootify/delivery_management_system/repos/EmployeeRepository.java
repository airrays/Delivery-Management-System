package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.controller.EmployeeController;
import io.bootify.delivery_management_system.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByName(String employeeName);

    List<Employee> findByStatusEqualsAndUpdateTimeIsAfter(Integer status,LocalDateTime updateTime);
    boolean existsByIdAndName(Long id, String name);
    Optional<Employee> findByUsername(String name);
    List<Employee> findByUsernameOrderByUpdateTimeAsc(String name);
    List<Employee> findByOrderByUpdateTimeAsc();
}
