/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Employee;

/**
 *
 * @author AMINE
 */
public interface EmployeeService {
        public Employee findByMatricule(Long matricule);
        public int createEmployee(Employee employee);
        public int editEmployee(Long matricule,Employee newEmployee);
}
