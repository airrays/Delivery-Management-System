package io.bootify.delivery_management_system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bootify.delivery_management_system.domain.Employee;
//import io.bootify.delivery_management_system.mapper.EmployeeMapper;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service //Let Spring manage it
//public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {  //provided by mybatis plus
//    @Autowired
//    private EmployeeMapper employeeMapper;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//}
@Slf4j
@Service //Let Spring manage it
public class EmployeeServiceImpl implements EmployeeService {  //provided by mybatis plus

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeByName(String name){
        log.info("========================");
        System.out.println(name);
        return employeeRepository.findEmployeeByName(name);
    }
}