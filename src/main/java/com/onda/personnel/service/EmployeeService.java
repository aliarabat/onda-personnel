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

    public Employee findByMatricule(Integer matricule);

    public int createEmployee(List<Employee> employees);

    public int editEmployee(Integer matricule, Employee newEmployee);

    public int deleteEmployee(Integer matricule);

    public List<Employee> findAll();
}
