/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.dao.WorkDetailDao;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.WorkDetailSevice;
import com.onda.personnel.service.WorkService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class WorkDetailSeviceImpl implements WorkDetailSevice{
    @Autowired
    WorkDetailDao workDetailDao;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    WorkService workService;

    

    @Override
    public List<WorkDetail> findByWorkDetailDate(LocalDate workDetailDate) {
        if(workDetailDao.findByWorkDetailDate(workDetailDate).isEmpty()){
            return null;
        }
        else
return workDetailDao.findByWorkDetailDate(workDetailDate);
    }

    public WorkDetailDao getWorkDetailDao() {
        return workDetailDao;
    }

    public void setWorkDetailDao(WorkDetailDao workDetailDao) {
        this.workDetailDao = workDetailDao;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    
    
    
    
    
}
