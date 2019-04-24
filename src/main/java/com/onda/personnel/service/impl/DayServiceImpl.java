/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.bean.Detail;
import com.onda.personnel.bean.Employee;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.service.DayDetailService;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.WorkDetailSevice;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.personnel.service.DetailService;

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
    private DetailService detailService;
    
    @Autowired
    private DayDetailService dayDetailService;

    @Override
    public int createDay(Integer matricule, List<Day> days) {
        Employee emp = employeeService.findByMatricule(matricule);
        if (emp == null) {
            return -1;
        } else if (days == null || days.isEmpty()) {
            return -2;
        } else {
            List<Day> daysSaved = new ArrayList<>();
            for (Day day : days) {
                daysSaved.add(setDayInfos(day, day.getDayDetails()));
            }
            //LocalDate workDetailDate = DateUtil.fromStringToLocalDate(date).plusDays(1);
            //instanciation of workDetail found and newWorkDetail
            workDetailSevice.createWorkDetail(emp, daysSaved);
            return 1;
        }
    }

    private Day setDayInfos(Day day, List<DayDetail> dayDetails) {
        day.setDayDetails(new ArrayList<>());
        Integer pan = 0, hn = 0, he = 0;
        for (DayDetail dayDetail : dayDetails) {
            Detail dd = detailService.findByWording(dayDetail.getDetail().getWording());
            dayDetail.setDetail(dd);
            DayDetail dayDetail1= dayDetailService.createDayDetail(dayDetail);
            day.getDayDetails().add(dayDetail1);
            pan += dd.getPan();
            hn += dd.getHn();
            he += dd.getHe();
        }
        day.setHn(hn);
        day.setHe(he);
        day.setPan(pan);
        dayDao.save(day);
        return day;
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

    public DetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

    public DayDetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DayDetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }

  

}
