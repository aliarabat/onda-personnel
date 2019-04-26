/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.service.WorkService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkDao workDao;

    @Override
    public void saveWork(Work work) {
        workDao.save(work);
    }

    @Override
    public void createWork(Employee emp, WorkDetail workDetail) {
        Work work = new Work();
        work.setEmployee(emp);
        work.setWorkDetail(workDetail);
        saveWork(work);
    }

    @Override
    public Work findByEmployeeMatriculeAndWorkDetailTestDate(Integer matricule, Date workDetailDate) {
        return workDao.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, workDetailDate);
    }

    @Override
    public Work findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(Integer matricule) {
        return workDao.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
    }

    @Override
    public Work findByEmployeeMatriculeAndMonthAndYear(Integer matricule, int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        Date theDate = DateUtil.toDate(localDate);
        Work theWork = findByEmployeeMatriculeAndWorkDetailTestDate(matricule, theDate);
        if (theWork == null) {
            return null;
        } else {
            return theWork;
        }

    }

    @Override
    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate) {
        return workDao.findByWorkDetailWorkDetailDate(workDetailDate);
    }

    @Override
    public List<Work> findByMonthAndYear(int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        Date theDate = DateUtil.toDate(localDate);
        List<Work> listOfWorksMonthly = new ArrayList<>();
        listOfWorksMonthly = findByWorkDetailWorkDetailDate(theDate);
        if (listOfWorksMonthly.isEmpty() == true || listOfWorksMonthly == null) {
            return null;
        } else {
            return listOfWorksMonthly;
        }
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

}
