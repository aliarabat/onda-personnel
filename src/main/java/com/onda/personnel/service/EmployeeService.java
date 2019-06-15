/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.onda.personnel.model.Employee;

/**
 *
 * @author AMINE
 */
public interface EmployeeService {

    Employee findByMatricule(Integer matricule);

    int createEmployee(List<Employee> employees);

    int editEmployee(Employee newEmployee);

    int deleteEmployee(Integer matricule);

    List<Employee> findByIsExist(boolean isExist);

    Employee getEmployeeById(Long id);

    int revert(Integer matricule);

    List<Employee> findByType(String type);

    long count();
}
