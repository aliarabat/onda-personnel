/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Work;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.service.WorkService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class WorkServiceImpl implements WorkService{
    @Autowired
    WorkDao workDao;
    

    @Override
    public Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, LocalDate workDetailDate) {
return workDao.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, workDetailDate);
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }
    
    
}
