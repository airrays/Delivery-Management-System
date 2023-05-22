package io.bootify.delivery_management_system;

import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//public class EmployeeRepositoryTest2 {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Sql(scripts = {"classpath:/init.sql"})
//    @Test
//    public void find_employee_age_older_than_18() {
//        List<Employee> employeeList = employeeRepository.findByAgeGreaterThan(18);
//        assertEquals(1, employeeList.size());
//    }
//
//    @Sql(scripts = {"classpath:/init.sql"})
//    @Test
//    public void should_get_user_info() {
//        Optional<EmployeeDTO> userInformation = employeeRepository.getUserInformation(1L);
//        System.out.println(userInformation.get().toString());
//    }
//
//    @Sql(scripts = {"classpath:/init.sql"})
//    @Test
//    public void should_get_user_info_list() {
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "age");
//        Page<EmployeeDTO> userInformationList = employeeRepository.getUserInformationList(pageRequest);
//        //查询结果总数
//        System.out.println(userInformationList.getTotalElements());// 6
//        //按照当前分页大小，总页数
//        System.out.println(userInformationList.getTotalPages());// 2
//        System.out.println(userInformationList.getContent());
//    }
//
//    @Sql(scripts = {"classpath:/init.sql"})
//    @Test
//    public void should_filter_user_info() {
//        List<String> employeeList=new ArrayList<>(Arrays.asList("employee1","employee2"));
//        List<EmployeeDTO> employeeDTOS = employeeRepository.filterUserInfo(employeeList);
//        System.out.println(employeeDTOS);
//    }
//
//    @Sql(scripts = {"classpath:/init.sql"})
//    @Test
//    public void should_filter_user_info_by_age() {
//        List<EmployeeDTO> employeeDTOS = employeeRepository.filterUserInfoByAge(19,20);
//        System.out.println(employeeDTOS);
//    }
//}