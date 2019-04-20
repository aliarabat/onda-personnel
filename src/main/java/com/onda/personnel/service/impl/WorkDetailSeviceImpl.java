/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.Employee;
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
public class WorkDetailSeviceImpl implements WorkDetailSevice {

    @Autowired
    private WorkService workService;
    @Autowired
    private WorkDetailDao workDetailDao;
    @Autowired
    private EmployeeService employeeService;

    /*@Override
    public List<WorkDetail> findByWorkDetailDate(LocalDate workDetailDate) {
        if (workDetailDao.findByWorkDetailDate(workDetailDate).isEmpty()) {
            return null;
        } else {
            return workDetailDao.findByWorkDetailDate(workDetailDate);
        }
    }*/
    @Override
    public WorkDetail findByWorkDetailDate(LocalDate localDate) {
        return workDetailDao.findByWorkDetailDate(localDate);
    }

    @Override
    public void saveWorkDetail(WorkDetail workDetail) {
        workDetailDao.save(workDetail);
    }

    @Override
    public void createWorkDetail(Employee emp, LocalDate workDetailDate, List<Day> days) {
        WorkDetail workDetail = findByWorkDetailDate(workDetailDate);
        WorkDetail newWorkDetail = new WorkDetail();
        int workDetailListLength = workDetailDate.lengthOfMonth();
        if (workDetail == null) {
            workDetail = new WorkDetail();
        }
        newWorkDetail.setWorkDetailDate(workDetailDate.plusMonths(1));
        for (Day day : days) {
            if (workDetailListLength > workDetail.getDays().size()) {
                workDetail.getDays().add(day);
                setOtherInfos(workDetail, day);
            } else {
                setOtherInfos(newWorkDetail, day);
                newWorkDetail.getDays().add(day);
            }
        }

        if (newWorkDetail.getDays() == null || newWorkDetail.getDays().isEmpty()) {
            if (workDetailListLength > workDetail.getDays().size()) {
                saveWorkDetail(workDetail);
            } else {
                saveWorkDetail(workDetail);
                workService.createWork(emp, workDetail);
            }
        } else {
            saveWorkDetail(workDetail);
            saveWorkDetail(newWorkDetail);
        }
    }

    private void setOtherInfos(WorkDetail workDetail, Day day) {
        workDetail.setPan(workDetail.getPan() + day.getPan());
        workDetail.setHn(workDetail.getHn() + day.getHn());
        workDetail.setHjf(workDetail.getHjf() + day.getHe());
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
