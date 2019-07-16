package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private List<Employee> employees_1 = new ArrayList<>();
    private List<Employee> employees_2 = new ArrayList<>();

    private void initData(){
        employees_1.add(new Employee(1,"Tony",22,"male",10000));
        employees_1.add(new Employee(2,"Jerry",25,"male",15000));

        employees_2.add(new Employee(3,"Glen",21,"male",14000));
        employees_2.add(new Employee(4,"Kevin",24,"male",8000));

        companies.add(new Company(1,"alibaba",10000,employees_1));
        companies.add(new Company(2,"tencent",10000,employees_2));
    }

    @GetMapping()
    public ResponseEntity getAllCompanies() {
        initData();
        return ResponseEntity.ok().body(companies);
    }

    @GetMapping("{companyId}")
    public ResponseEntity getSpecificCompany(@PathVariable int companyId) {
        initData();
        for (Company company : companies) {
            if (company.getCompanyId() == companyId) {
                return ResponseEntity.ok().body(company);
            }
        }
        return null;
    }

    @GetMapping("{companyId}/employees")
    public ResponseEntity getCompanyEmployees(@PathVariable Integer companyId) {
        initData();
        for (Company company : companies) {
            if (company.getCompanyId() == companyId) {
                return ResponseEntity.ok().body(company.getEmployees());
            }
        }
        return null;
    }



    @PostMapping()
    public ResponseEntity createConpany(@RequestBody Company company) {
        companies.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{companyId}")
    public ResponseEntity updateCompanyInfomation(@PathVariable int companyId) {
        initData();
        for (Company company : companies) {
            if (company.getCompanyId() == companyId) {
                company.setCompanyName("wangyi");
            }
        }
        return ResponseEntity.ok().body(companies);
    }

    @DeleteMapping("{companyId}")
    public ResponseEntity deleteCompany(@PathVariable int companyId) {
        initData();
        for (Company company : companies) {
            if (company.getCompanyId() == companyId) {
                companies.remove(company);
            }
        }
        return ResponseEntity.ok().body(companies);
    }




}
