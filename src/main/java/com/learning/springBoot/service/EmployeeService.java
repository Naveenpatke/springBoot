package com.learning.springBoot.service;

import com.learning.springBoot.aspect.TimeTracker;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @TimeTracker
    public String employeeName(){
        return "Naveen";
    }
}
