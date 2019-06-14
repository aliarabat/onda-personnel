
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.personnel.dao.WorkDetailDao;
import com.onda.personnel.model.Day;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Work;
import com.onda.personnel.model.WorkDetail;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.WorkDetailService;
import com.onda.personnel.service.WorkService;
import com.onda.personnel.util.DateUtil;
import com.onda.personnel.util.DayComparator;

/**
 * @author AMINE
 */
@Service
public class WorkDetailSeviceImpl implements WorkDetailService {

    @Autowired
    private WorkService workService;
    @Autowired
    private WorkDetailDao workDetailDao;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DayService dayService;

    private static final Logger log = LoggerFactory.getLogger(WorkDetailSeviceImpl.class);

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
    public void createWorkDetail(Employee emp, List<Day> days) {
        Work work = workService.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(emp.getMatricule());
        WorkDetail workDetail;
        LocalDate dayDate;
        int workDetailListLength = DateUtil.lenghtOfMonth(DateUtil.getFirstDayOfWeek());
        if (work == null || work.getWorkDetail() == null) {
            work = new Work(emp);
            //Date firstMondayOfMonth=DateUtil.toDate(DateUtil.getFirstMonday(DayOfWeek.MONDAY));
            //for tests
            //Date firstDayOfMonth = DateUtil.toDate(DateUtil.getFirstDayOfMonth());
            Date firstDayOfMonth = DateUtil.getFirstDayOfMonth();
            workDetail = new WorkDetail(firstDayOfMonth);
            workDetailListLength = workDetailListLength - DateUtil.getFirstDayOfWeek().getDayOfMonth() + 1;
            dayDate = DateUtil.getFirstDayOfWeek();
            //for tests
            /*workDetailListLength = workDetailListLength - DateUtil.getFirstDayOfMonth().getDayOfMonth() + 1;
            dayDate = DateUtil.getFirstDayOfMonth();*/
        } else {
            workDetail = workDetailDao.getOne(work.getWorkDetail().getId());
            int size = workDetail.getDays().size();
            Day dayMin = Collections.min(workDetail.getDays(), new DayComparator());
            workDetailListLength = DateUtil.fromDate(workDetail.getWorkDetailDate()).lengthOfMonth() - dayMin.getDayDate().getDate() + 1;
            dayDate = DateUtil.fromDate(workDetail.getDays().get(size - 1).getDayDate()).plusDays(1);
        }

        LocalDate ld = DateUtil.fromDate(workDetail.getWorkDetailDate()).plusMonths(1);
        WorkDetail newWorkDetail = new WorkDetail(DateUtil.toDate(ld));
        try {
            for (Day day : days) {
                if (workDetailListLength > workDetail.getDays().size()) {
                    setOtherInfos(workDetail, dayService.setDayInfos(day.getDayDetails(), DateUtil.toDate(dayDate)));
                } else {
                    setOtherInfos(newWorkDetail, dayService.setDayInfos(day.getDayDetails(), DateUtil.toDate(dayDate)));
                }
                dayDate = dayDate.plusDays(1);
            }
        } catch (NullPointerException e) {
            log.error("null in for-loop block " + e.getMessage());
        }
        if (newWorkDetail.getDays() == null || newWorkDetail.getDays().isEmpty()) {
            saveWorkDetail(workDetail);
            work.setWorkDetail(workDetail);
            workService.saveWork(work);
        } else {
            saveWorkDetail(workDetail);
            saveWorkDetail(newWorkDetail);
            if (work.getWorkDetail() == null) {
                work.setWorkDetail(workDetail);
            }
            Work newWork = new Work(emp, newWorkDetail);
            workService.saveWork(work);
            workService.saveWork(newWork);
        }
    }

    @Override
    public WorkDetail updateWorkDetail(WorkDetail workDetail) {
        WorkDetail wd = workDetailDao.getOne(workDetail.getId());
        if (wd == null) {
            return null;
        } else {
            wd.getHjf().setHour(workDetail.getHjf().getHour());
            wd.getHjf().setMinute(workDetail.getHjf().getMinute());
            wd.getHn().setHour(workDetail.getHn().getHour());
            wd.getHn().setMinute(workDetail.getHn().getMinute());
            wd.setPan(workDetail.getPan());
            saveWorkDetail(wd);
            return wd;
        }
    }

    private void setOtherInfos(WorkDetail workDetail, Day day) {
        int hoursHjfWorked = workDetail.getHjf().getHour() + day.getHe().getHour();
        int minutesHjfWorked = workDetail.getHjf().getMinute() + day.getHe().getMinute();
        int hoursHnWorked = workDetail.getHn().getHour() + day.getHn().getHour();
        int minutesHnWorked = workDetail.getHn().getMinute() + day.getHn().getMinute();
        if (minutesHnWorked >= 60) {
            hoursHnWorked++;
            minutesHnWorked -= 60;
        }
        if (minutesHjfWorked >= 60) {
            hoursHjfWorked++;
            minutesHjfWorked -= 60;
        }
        workDetail.setPan(workDetail.getPan() + day.getPan());
        workDetail.getHn().setHour(hoursHnWorked);
        workDetail.getHn().setMinute(minutesHnWorked);
        workDetail.getHjf().setHour(hoursHjfWorked);
        workDetail.getHjf().setMinute(minutesHjfWorked);
        workDetail.getDays().add(day);
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

    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

    @Override
    public WorkDetail findByEmployeeMatriculeAndWorkDetailDate(Integer matricule, int year, int month) {
        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return null;
        } else {

            LocalDate localDate = LocalDate.of(year, month, 1);
            Date theDate = DateUtil.toDate(localDate);
            Work work = workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, theDate);
            if (work == null) {
                return null;
            } else {
                WorkDetail workDetail = work.getWorkDetail();
                return workDetail;
            }
        }
    }
}
