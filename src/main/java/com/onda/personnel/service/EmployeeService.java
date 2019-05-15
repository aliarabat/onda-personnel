/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.List;

import com.onda.personnel.model.Employee;
import javax.servlet.http.HttpServletResponse;

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

    void print(HttpServletResponse response, Integer matricule);

    public Employee getEmployeeById(Long id);

    public int revert(Integer matricule);

    public List<Employee> findByType(String type);
}
