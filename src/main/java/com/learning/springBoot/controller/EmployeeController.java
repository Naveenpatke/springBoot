package com.learning.springBoot.controller;

import com.learning.springBoot.aspect.TimeTracker;
import com.learning.springBoot.repository.EmployeeRepository;
import com.learning.springBoot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public String getEmployees() {
        return employeeService.employeeName();
    }
}
