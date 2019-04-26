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
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.service.DayDetailService;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.personnel.service.DetailService;
import com.onda.personnel.service.WorkDetailService;
import com.onda.personnel.service.WorkService;
import java.time.LocalDate;
import java.util.Date;

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
    private WorkDetailService workDetailService;
    @Autowired
    private WorkService workService;
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
            //instanciation of workDetail found and newWorkDetail
            workDetailService.createWorkDetail(emp, daysSaved);
            return 1;
        }
    }

    private Day setDayInfos(Day day, List<DayDetail> dayDetails) {
        Integer pan = 0, hn = 0, he = 0;
        for (DayDetail dayDetail : dayDetails) {
            Detail dd = detailService.findByWording(dayDetail.getDetail().getWording());
            dayDetail.setDetail(dd);
            DayDetail dayDetail1 = dayDetailService.createDayDetail(dayDetail);
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

    @Override
    public List<Day> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(Integer matricule, int year, int month) {
        Work work = workService.findByEmployeeMatriculeAndMonthAndYear(matricule, year, month);
        if (work == null) {
            return null;
        } else {
            return work.getWorkDetail().getDays();
        }
    }

    @Override
    public Day findByEmployeeMatriculeAndDateOfTheDay(Integer matricule, Date dayDate) {
        LocalDate localDate = DateUtil.fromDate(dayDate);
        LocalDate checklocalDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        Date tmpDate = DateUtil.toDate(checklocalDate);
        Work work = workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule, tmpDate);
        if (work == null) {
            return null;
        } else {
            WorkDetail workDetail = work.getWorkDetail();
            List<Day> listOfDays = workDetail.getDays();
            Day theDay = new Day();
            for (Day day : listOfDays) {
                if (day.getDayDate().compareTo(dayDate) == 0) {
                    theDay = dayDao.getOne(day.getId());
                }
            }
            if(theDay==null){
                return null;
            }
            else{
                return theDay;
            }

        }

    }
    
    @Override
    public List<Day> findByDayDate(Date dayDate) {
        return dayDao.findByDayDate(dayDate);

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

    public WorkDetailService getWorkDetailService() {
        return workDetailService;
    }

    public void setWorkDetailService(WorkDetailService workDetailService) {
        this.workDetailService = workDetailService;
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
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
