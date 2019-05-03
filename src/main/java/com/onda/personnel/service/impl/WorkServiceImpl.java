/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Work;
import com.onda.personnel.model.WorkDetail;
import com.onda.personnel.service.WorkService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
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
    public List<String> findFromDateToDate(Integer matricule) {
        Work work = findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
        List<String> dateList = new ArrayList<>(2);
        LocalDate fromDate;
        LocalDate toDate;
        if (work == null) {
            fromDate = DateUtil.getFirstDayOfWeek();
            //toDate = fromDate.plusDays(6);
        } else {
            int size = work.getWorkDetail().getDays().size();
            fromDate = DateUtil.fromDate(work.getWorkDetail().getDays().get(size - 1).getDayDate()).plusDays(1);
        }
        toDate = fromDate.plusDays(6);
        dateList.add(0, fromDate.toString());
        dateList.add(1, toDate.toString());
        return dateList;
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
        List<Work> listOfWorksMonthly = findByWorkDetailWorkDetailDate(theDate);
        if (listOfWorksMonthly.isEmpty() || listOfWorksMonthly == null) {
            return null;
        } else {
            return listOfWorksMonthly;
        }
    }

    @Override
    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate) {
        return workDao.findByWorkDetailWorkDetailDate(workDetailDate);

    }

    @Override
    public List<Work> findWorksByDate(Date workDate) {
        LocalDate checklocalDate = DateUtil.fromDate(workDate);
        LocalDate localDate = LocalDate.of(checklocalDate.getYear(), checklocalDate.getMonth(), 1);
        Date theDate = DateUtil.toDate(localDate);
        return findByWorkDetailWorkDetailDate(theDate);
    }

}
