package io.bootify.delivery_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.bootify.delivery_management_system.common.R;
import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.service.EmployeeService;
import io.bootify.delivery_management_system.service.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j //log
@RestController
@RequestMapping("/employee")  //localhost:8080/employee/login
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Employee login
     * @param request
     * @param employee
     * @return
     * encrpyt password md5
     * Query by username
     * Check password match
     * Check employee account status
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,  @RequestBody Employee employee) {
        String password=employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        LambdaQueryWrapper<Employee> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp=employeeService.getOne(queryWrapper);

        if(emp==null){
            return R.error("Login Fail -1");
        }
        if(!emp.getPassword().equals(password)){
            return R.error("Wrong Password");
        }
        if(emp.getStatus()==0){
            return R.error("Account Disabled");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
}
