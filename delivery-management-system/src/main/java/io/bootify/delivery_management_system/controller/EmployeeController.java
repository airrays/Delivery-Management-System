package io.bootify.delivery_management_system.controller;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bootify.delivery_management_system.common.R;
import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import io.bootify.delivery_management_system.service.EmployeeService;
import io.bootify.delivery_management_system.service.EmployeeServiceImpl;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        log.info(request.getSession().getId());
        log.info((String) request.getSession().getAttribute("employee"));
        employee.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId=(Long)request.getSession().getAttribute("employee");
        log.info("Employee INFO: {}",employee.toString());
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeRepository.save(employee);
        return null;
    }

    /**
     * employee page search
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public List<EmployeeRecord> page(int page, int pageSize, @Nullable String name){
        log.info("page = {}, pageSize= {}, name= {}",page,pageSize,name);
        //Page pageInfo= new Page(page, pageSize);
        //LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        List<EmployeeRecord> employees= new ArrayList<EmployeeRecord>();
        if(name!=null){
            employees=employeeRepository.findByNameOrderByUpdateTimeAsc(name);
            return employees;
        }
        else{
            employees=employeeRepository.findByOrderByUpdateTimeAsc();
            return employees;
        }
        //return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());
        Long empId=(Long) request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        try {
            Employee emp=employeeRepository.save(employee);
            log.info(String.valueOf(ResponseEntity.ok(emp.toString())));
            return R.success("Updated Employee");
        }catch (Exception e){
            log.warn(e.getMessage());
            return R.error(e.getMessage());
        }
    }
    @GetMapping("{id}")
    private EmployeeRecord getEmployee(@PathVariable Long id){
//        Optional<Employee> employee = employeeRepository.findById(id);
//        // map user data to UserRecord DTO
//        if(employee!=null) {
//            return new EmployeeRecord(employee.get().getUsername(), employee.get().getPhone());
//        }
//        return new EmployeeRecord("Null","Null");
        Employee employee= (Employee) employeeService.updateEmployee(id);
        return new EmployeeRecord(employee.getUsername(),employee.getPhone());
    }
    public record EmployeeRecord(String name, String phone){}

}
