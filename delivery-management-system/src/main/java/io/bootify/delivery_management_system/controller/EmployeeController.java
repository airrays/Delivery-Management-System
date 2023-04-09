package io.bootify.delivery_management_system.controller;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.bootify.delivery_management_system.common.R;
import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import io.bootify.delivery_management_system.service.EmployeeService;
import io.bootify.delivery_management_system.service.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//import com.baomidou.mybatisplus.autoconfigure.*;
//import com.baomidou.*;

@Slf4j //log
@RestController
@RequestMapping("/employee")  //localhost:8080/employee/login
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServiceImpl employeeService;
    /**
     * Employee login
     * @param request
     * @param employee
     * @return
     * encrpyt password md5
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request,  @RequestBody Employee employee) {
        //System.out.println("Process REQUEST POST");
        //System.out.println(employee);
        String password=employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //System.out.println(employee.getName()+" "+ password);
        Optional<Employee> emp =employeeRepository.findByUsername(employee.getName());
        if(emp.get()==null){
            return "Login Fail -1";
        }
        if(!emp.get().getPassword().equals(password)){
            return "Wrong Password";
        }
        if(emp.get().getStatus()==0){
            return "Account Disabled";
        }
        request.getSession().setAttribute("employee",emp.get().getId());
        log.info("Session ID {}", request.getSession().getId());
        return JSON.toJSONString(R.success(emp));
    }

    //Employee Log Out
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request, @RequestBody Employee employee){
        //清理Session中保存的当前员工登录的id
        request.getSession().removeAttribute("employee");
        log.info(employee.getName()+" Log out ");
        return R.success("Log out");
    }

    /**
     * New Employee
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("New Employee, Employee Info: {} ",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId=(Long)request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeRepository.save(employee);
        return null;
    }


}
