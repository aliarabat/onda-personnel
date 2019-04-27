/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Skip;
import com.onda.personnel.dao.SkipDao;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.SkipService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JaafarDiyaou
 */
@Service
public class SkipServiceImpl implements SkipService {
    @Autowired
    SkipDao skipDao;
    @Autowired
    EmployeeService employeeService;

    @Override
    public List<Skip> findByEmployeeMatriculeAndSkipDate(Integer matricule, Date skipDate) {
        return skipDao.findByEmployeeMatriculeAndSkipDate(matricule, skipDate);
    }

    @Override
    public Skip createSkip(Integer matricule, Skip skip) {
        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return null;
        } else {
            skip.setEmployee(employee);
            return skipDao.save(skip);
        }
    }


    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public SkipDao getSkipDao() {
        return skipDao;
    }

    public void setSkipDao(SkipDao skipDao) {
        this.skipDao = skipDao;
    }


}
