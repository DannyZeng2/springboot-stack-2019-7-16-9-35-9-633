package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private List<Employee> employees = new ArrayList<>();

    private void initData() {
        employees.add(new Employee(1, "Tony", 22, "male", 10000));
        employees.add(new Employee(2, "Jerry", 25, "male", 15000));
    }

    @GetMapping()
    public ResponseEntity getAllEmployees() {
        initData();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("{id}")
    public ResponseEntity getSpecificEmployee(@PathVariable int id) {
        initData();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return ResponseEntity.ok().body(employee);
            }
        }
        return null;
    }

    @GetMapping()
    public ResponseEntity getCurrPageEmployee(@RequestParam int page,@RequestParam int pageSize) {
        initData();
        List<Employee> employees_new = new ArrayList<>();

        int fromIndex = (page-1)*pageSize;
        int toIndex = (page-1)*pageSize+pageSize+employees.size()%pageSize;
        employees_new = employees.subList(fromIndex,2);

        return ResponseEntity.ok().body(employees_new);
    }

    @GetMapping()
    public ResponseEntity getMaleEmployee(@RequestParam("gender") String gender) {
        initData();
        List<Employee> employee_male = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGender() == gender) {
                employee_male.add(employee);
            }
        }
        return ResponseEntity.ok().body(employee_male);
    }

    @PostMapping("")
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        initData();
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateEmployeeInfomation(@PathVariable int id) {
        initData();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName("Sally");
                employee.setGender("female");
            }
        }
        return ResponseEntity.ok().body(employees);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id) {
        initData();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
            }
        }
        return ResponseEntity.ok().body(employees);
    }


}
