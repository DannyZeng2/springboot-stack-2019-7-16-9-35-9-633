package com.tw.apistackbase.controller;

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


    @GetMapping()
    public ResponseEntity getAllEmployees() {
        initData();
        return ResponseEntity.ok().body(employees);
    }
}
