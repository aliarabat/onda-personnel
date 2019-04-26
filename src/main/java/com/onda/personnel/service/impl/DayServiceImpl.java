/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.*;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
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
    private DetailService detailService;
    
    @Autowired
    private DayDetailService dayDetailService;

    @Autowired
    private WorkService workService;

    public static Jsr310JpaConverters.LocalDateConverter localDateConverter=new Jsr310JpaConverters.LocalDateConverter();

    @Override
    public int createDay(Integer matricule, List<Day> days) {
        Employee emp = employeeService.findByMatricule(matricule);
        if (emp == null) {
            return -1;
        } else if (days == null || days.isEmpty()) {
            return -2;
        } else if (days.size()>7 || days.size()<6){
            return -3;
        } else {
            List<Day> daysSaved = new ArrayList<>();
            LocalDate ld=DateUtil.getFirstDayOfWeek();
            for (Day day : days) {
                daysSaved.add(setDayInfos(day.getDayDetails(),DateUtil.toDate(ld)));
                ld=ld.plusDays(1);
            }
            workDetailSevice.createWorkDetail(emp, daysSaved);
            return 1;
        }
    }

    private Day setDayInfos(List<DayDetail> dayDetails, Date ld) {
        Integer pan = 0, hn = 0, he = 0;
        Day day=new Day();
        day.setDayDate(ld);
        for (DayDetail dayDetail : dayDetails) {
            Detail dd = detailService.findByWording(dayDetail.getDetail().getWording());
            DayDetail dayDet=new DayDetail();
            dayDet.setDetail(dd);
            DayDetail dayDetail1= dayDetailService.createDayDetail(dayDet);
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
