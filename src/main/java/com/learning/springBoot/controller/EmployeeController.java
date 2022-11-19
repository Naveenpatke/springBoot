package com.learning.springBoot.controller;

import com.learning.springBoot.repository.EmployeeRepository;
import com.learning.springBoot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public ResponseEntity<String> getEmployees() {
        System.out.println("log");
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body("Employees");
    }
}
