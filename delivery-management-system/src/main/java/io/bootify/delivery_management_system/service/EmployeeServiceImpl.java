package io.bootify.delivery_management_system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.mapper.EmployeeMapper;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Let Spring manage it
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {  //provided by mybatis plus
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;
}
