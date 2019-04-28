/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Employee;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface EmployeeService {

    Employee findByMatricule(Integer matricule);

    int createEmployee(List<Employee> employees);

    int editEmployee(Employee newEmployee);

    int deleteEmployee(Integer matricule);
    
    public List<Employee> findByIsExist(boolean isExist);

}
