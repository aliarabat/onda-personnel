/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.dao.EmployeeDao;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.WorkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    private WorkService workService;

    @Override
    public Employee findByMatricule(Integer matricule) {
        return employeeDao.findByMatricule(matricule);
    }

    @Override
    public int createEmployee(Employee employee) {
        Employee checkEmployee = findByMatricule(employee.getMatricule());
        if (checkEmployee != null) {
            return -1;
        } else {
            employeeDao.save(employee);
            return 1;
        }

    }

    @Override
    public int editEmployee(Integer matricule, Employee newEmployee) {
        Employee checkEmployee = findByMatricule(matricule);
        if (checkEmployee == null) {
            return -1;
        } else {
            checkEmployee.setFirstName(newEmployee.getFirstName());
            checkEmployee.setLastName(newEmployee.getLastName());
            checkEmployee.setMatricule(newEmployee.getMatricule());
            employeeDao.save(checkEmployee);
            return 1;
        }
    }

    @Override
    public int deleteEmployee(Integer matricule) {
        Employee checkEmployee = findByMatricule(matricule);
        if (checkEmployee == null) {
            return -1;
        } else {
            if (-1 == -1) {
                return -2;
            } else {
                employeeDao.delete(checkEmployee);
                return 1;
            }
        }
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

}
