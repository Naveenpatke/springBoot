package com.learning.springBoot.controller;

import com.learning.springBoot.model.Employee;
import com.learning.springBoot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return repository.findAll();
    }
}
