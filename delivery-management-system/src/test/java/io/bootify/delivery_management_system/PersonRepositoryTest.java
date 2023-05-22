package io.bootify.delivery_management_system;

import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//public class EmployeeRepositoryTest {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    private Long id;
//
//    /**
//     * Save Person into Database
//     */
//    @BeforeEach
//    public void setUp() {
//        assertNotNull(employeeRepository);
//        Employee employee = new Employee("SnailClimb", 23);
//        Employee savedEmployee = employeeRepository.saveAndFlush(employee);// 更新 employee 对象的姓名
//        savedEmployee.setName("UpdatedName");
//        employeeRepository.save(savedEmployee);
//
//        id = savedEmployee.getId();
//    }
//
//    /**
//     * Using JPA method to find employee
//     */
//    @Test
//    public void should_get_employee() {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        assertTrue(employeeOptional.isPresent());
//        assertEquals("SnailClimb", employeeOptional.get().getName());
//        assertEquals(Integer.valueOf(23), employeeOptional.get().getAge());
//
//        List<Employee> employeeList = employeeRepository.findByAgeGreaterThan(18);
//        assertEquals(1, employeeList.size());
//        // 清空数据库
//        employeeRepository.deleteAll();
//    }
//
//    /**
//     * 自定义 query sql 查询语句查找 employee
//     */
//
//    @Test
//    public void should_get_employee_use_custom_query() {
//        // 查找所有字段
//        Optional<Employee> employeeOptional = employeeRepository.findByNameCustomeQuery("SnailClimb");
//        assertTrue(employeeOptional.isPresent());
//        assertEquals(Integer.valueOf(23), employeeOptional.get().getAge());
//        // 查找部分字段
//        String employeeName = employeeRepository.findEmployeeNameById(id);
//        assertEquals("SnailClimb", employeeName);
//        System.out.println(id);
//        // 更新
//        employeeRepository.updateEmployeeNameById("UpdatedName", id);
//        Optional<Employee> updatedName = employeeRepository.findByNameCustomeQuery("UpdatedName");
//        assertTrue(updatedName.isPresent());
//        // 清空数据库
//        employeeRepository.deleteAll();
//    }

//}
