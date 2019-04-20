/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.service.DayDetailService;
import com.onda.personnel.service.DayService;
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
public class DayServiceImpl implements DayService {

    @Autowired
    private DayDao dayDao;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WorkDetailSevice workDetailSevice;

    @Autowired
    private DayDetailService dayDetailService;

    @Override
    public int createDay(Integer matricule, String date, List<Day> days) {
        Employee emp = employeeService.findByMatricule(matricule);
        if (emp == null) {
            return -1;
        } else if (days == null || days.isEmpty()) {
            return -2;
        } else if (days.size() > 7) {
            return -3;
        } else {
            for (Day day : days) {
                setDayInfos(day, day.getDayDetails());
            }
            LocalDate workDetailDate = DateUtil.fromStringToLocalDate(date);
            //instanciation of workDetail found and newWorkDetail
            workDetailSevice.createWorkDetail(emp, workDetailDate, days);

            return 1;
        }
    }

    private void setDayInfos(Day day, List<DayDetail> dayDetails) {
        Integer pan = 0;
        Integer hn = 0;
        Integer he = 0;
        for (DayDetail dayDetail : dayDetails) {
            DayDetail dd=dayDetailService.createDayDetail(dayDetail);
            pan += dd.getPan();
            hn += dd.getHn();
            he += dd.getHe();
        }
        day.setHn(hn);
        day.setHe(he);
        day.setPan(pan);
        dayDao.save(day);
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public DayDao getDayDao() {
        return dayDao;
    }

    public void setDayDao(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    public WorkDetailSevice getWorkDetailSevice() {
        return workDetailSevice;
    }

    public void setWorkDetailSevice(WorkDetailSevice workDetailSevice) {
        this.workDetailSevice = workDetailSevice;
    }

    public DayDetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DayDetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }

}
