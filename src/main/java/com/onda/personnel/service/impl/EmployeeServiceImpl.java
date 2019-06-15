/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.personnel.dao.EmployeeDao;
import com.onda.personnel.model.Employee;
import com.onda.personnel.service.EmployeeService;

/**
 * @author AMINE
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public Employee findByMatricule(Integer matricule) {
        return employeeDao.findByMatricule(matricule);
    }

    @Override
    public int createEmployee(List<Employee> employees) {
        for (Employee cheEmployee : employees) {
            Employee checkEmployee2 = findByMatricule(cheEmployee.getMatricule());
            if (checkEmployee2 == null) {
                cheEmployee.setIsExist(true);
                employeeDao.save(cheEmployee);
            }
        }
        return 1;
    }

    @Override
    public int editEmployee(Employee newEmployee) {
        Employee checkEmployee = employeeDao.getOne(newEmployee.getId());
        if (checkEmployee == null) {
            return -1;
        } else {
            checkEmployee.setFirstName(newEmployee.getFirstName());
            checkEmployee.setLastName(newEmployee.getLastName());
            checkEmployee.setMatricule(newEmployee.getMatricule());
            checkEmployee.setFonction(newEmployee.getFonction());
            checkEmployee.setType(newEmployee.getType());
            checkEmployee.setIsExist(true);
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
            checkEmployee.setIsExist(false);
            employeeDao.save(checkEmployee);
            return 1;
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.getOne(id);
    }

    @Override
    public List<Employee> findByIsExist(boolean isExist) {
        return employeeDao.findByIsExist(isExist);
    }

    @Override
    public int revert(Integer matricule) {
        Employee checkEmployee = findByMatricule(matricule);
        if (checkEmployee == null) {
            return -1;
        } else {
            checkEmployee.setIsExist(true);
            employeeDao.save(checkEmployee);
            return 1;
        }
    }

    @Override
    public List<Employee> findByType(String type) {
        return employeeDao.findByType(type);
    }

    @Override
    public long count() {
        return employeeDao.count();
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
