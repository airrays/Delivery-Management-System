package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByName(String employeeName);
    boolean existsByIdAndName(Long id, String name);
    Optional<Employee> findByUsername(String name);
}
