package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
