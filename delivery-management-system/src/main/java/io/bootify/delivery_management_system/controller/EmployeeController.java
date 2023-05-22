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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

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
    public ResponseEntity<EmployeeRecord> save(HttpServletRequest request, @RequestBody Employee employee){
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
        return ResponseEntity.status(HttpStatus.OK).body(new EmployeeRecord(employee.getId(), employee.getName(), employee.getStatus(),employee.getUpdateUser()));
    }

    /**
     * employee page search
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<?> page(int page, int pageSize, @Nullable String name){
        log.info("page = {}, pageSize= {}, name= {}",page,pageSize,name);
        //Page pageInfo= new Page(page, pageSize);
        //LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        List<Employee> employees;
        if(name!=null){
            employees=employeeRepository.findByUsernameOrderByUpdateTimeAsc(name);
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
        else{
            employees=employeeRepository.findByOrderByUpdateTimeAsc();
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
        //return R.success(pageInfo);
    }

    /***
     * Update Employee some values {DEPRECATED} TESTING ONLY
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public String update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());
        Long empId=(Long) request.getSession().getAttribute("employee");
        log.debug(String.valueOf(empId));
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        try {
            Employee emp=employeeRepository.save(employee);
            log.info(String.valueOf(ResponseEntity.ok(emp.toString())));
            return JSON.toJSONString(R.success("Updated Employee"));
        }catch (Exception e){
            log.warn(e.getMessage());
            return JSON.toJSONString(R.error(e.getMessage()));
        }
    }

    /***
     * Update Employee some values
     * @param request
     * @param employee
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<EmployeeRecord> updateEmployee(HttpServletRequest request, @RequestBody Employee employee){
        Long empId=(Long) request.getSession().getAttribute("employee");
        long id=Thread.currentThread().getId();
        log.info("Thread Id: {}", id);
        log.debug(String.valueOf(empId));
        log.info(employee.toString());
        Employee emp= (Employee) employeeService.updateEmployee(employee);
        if(emp != null){
            log.info("PUT Employee Username Name Updated");
        }
        return ResponseEntity.ok(new EmployeeRecord(emp.getId(),emp.getUsername(),emp.getStatus(),emp.getUpdateUser()));
    }

    /***
     * Get Employee, return EmployeeRecord
     * @param id
     * @return
     */
    @GetMapping("{id}")
    private ResponseEntity<?> getEmployee(@PathVariable Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
//        // map user data to UserRecord DTO
        if(employee!=null) {
            return ResponseEntity.ok(new EmployeeRecord(employee.get().getId(), employee.get().getUsername(), employee.get().getStatus(), employee.get().getUpdateUser()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found by ID");
        //return ResponseEntity.notFound().build();
//        return new EmployeeRecord("Null","Null");
        //Employee employee= (Employee) employeeService.updateEmployee(id);
        //return new EmployeeRecord(employee.getUsername(),employee.getPhone());
    }
    public record EmployeeRecord(@Nullable Long id, String userName, Integer status, Long updatedUser){}
}
