/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RestController
@RequestMapping("/personnel-api/personnels")
public class EmployeeRest {
    @Autowired
    EmployeeService employeeService;
    
    @GetMapping("/")
    public Employee findByMatricule(@PathVariable Long matricule) {
        return employeeService.findByMatricule(matricule);
    }

@PostMapping("/")
public int createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

@PutMapping("/matricule/{matricule}")
public int editEmployee(@PathVariable Long matricule,@RequestBody Employee newEmployee) {
        return employeeService.editEmployee(matricule, newEmployee);
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    

    

    
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    
}
