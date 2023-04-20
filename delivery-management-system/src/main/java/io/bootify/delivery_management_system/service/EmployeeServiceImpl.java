package io.bootify.delivery_management_system.service;

import io.bootify.delivery_management_system.common.R;
import io.bootify.delivery_management_system.domain.Employee;
//import io.bootify.delivery_management_system.mapper.EmployeeMapper;
import io.bootify.delivery_management_system.repos.EmployeeRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public Employee getEmployeeByName(String name){
        log.info("========================EmployeeServiceImpl:getEmployeeByName");
        System.out.println(name);
        return employeeRepository.findEmployeeByName(name);
    }

    @Transactional
    public <T> Object updateEmployee(Long updateUserId){
        Employee originalEmployee=employeeRepository.findById(updateUserId)
                .orElseThrow(()->new RuntimeException("EMPLOYEE NOT FOUND"));
        log.info(originalEmployee.toString());
        originalEmployee.setUpdateTime(LocalDateTime.now());
        originalEmployee.setUpdateUser(updateUserId);
        try {
            Employee emp=employeeRepository.save(originalEmployee);
            log.info(String.valueOf(ResponseEntity.ok(emp.toString())));
            if(!emp.getVersion().equals(originalEmployee.getVersion())){
                throw new OptimisticLockException();
            }
            return emp;
        }catch (Exception e){
            log.warn(e.getMessage());
            return R.error(e.getMessage());
        }
    }
}