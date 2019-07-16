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

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private List<Company> companies = new ArrayList<>();

    private List<Employee> employees = new ArrayList<>();

    private void initData(){
        employees.add(new Employee(1,"Tony",22,"male",10000));
        employees.add(new Employee(2,"Jerry",25,"male",15000));

        companies.add(new Company(1,"alibaba",10000,employees));
    }

    @GetMapping()
    public ResponseEntity getAllCompanies() {
        initData();
        return ResponseEntity.ok().body(companies);
    }


}
