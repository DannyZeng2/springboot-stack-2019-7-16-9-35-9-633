package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    GET       /employees/1  # obtain a certain specific employee
//    GET       /employees?page=1&pageSize=5  #Page query, page equals 1, pageSize equals 5
//    GET       /employees?gender=male   #screen all male employees
//    POST      /employees    # add an employee
//    PUT       /employees/1  #update an employee
//    DELETE    /employees/1  #delete an employee

    @GetMapping()
    public ResponseEntity getAllEmployees() {
        initData();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("{id}")
    public ResponseEntity getSpecificEmployees(@PathVariable int id) {
        initData();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return ResponseEntity.ok().body(employee);
            }
        }
        return null;
    }
}
