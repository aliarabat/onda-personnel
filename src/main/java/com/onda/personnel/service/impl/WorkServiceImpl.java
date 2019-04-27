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
    public Work findByEmployeeMatriculeAndWorkDetailTestDate(Integer matricule, Date workDetailDate) {
        return workDao.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, workDetailDate);
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule) {
        return workDao.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
    }

    @Override
    public List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Integer annee) {
        LocalDate ldStart = LocalDate.of(annee, 1, 1);
        LocalDate ldEnd = LocalDate.of(annee, 12, 31);
        return workDao.findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(matricule, DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));
    }

    @Override
    public List<Work> findAllByWorkDetailWorkDetailDateBetween(Integer annee) {
        LocalDate ldStart = LocalDate.of(annee, 1, 1);
        LocalDate ldEnd = LocalDate.of(annee, 12, 31);
        return workDao.findByWorkDetailWorkDetailDateBetween(DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));
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

    @Override
    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate) {
        return workDao.findByWorkDetailWorkDetailDate(workDetailDate);

    }

}
