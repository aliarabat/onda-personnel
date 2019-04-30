/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.*;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.betweenDate;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.service.*;

import java.time.LocalDate;
import java.time.Month;
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

    @Autowired
    private VacationService vacationService;

    @Override
    public int createDay(Integer matricule, List<Day> days) {
        Employee emp = employeeService.findByMatricule(matricule);
        if (emp == null) {
            return -1;
        } else if (days == null || days.isEmpty()) {
            return -2;
        } /*else if (days.size() > 7 || days.size() < 6) {
            return -3;
        } */else {
            workDetailService.createWorkDetail(emp, days);
            return 1;
        }
    }

    @Override
    public Day setDayInfos(List<DayDetail> dayDetails, Date ld) {
        Day day = new Day();
        Timing hn = new Timing(0, 0);
        Timing he = new Timing(0, 0);
        int pan = 0;
        day.setDayDate(ld);
        System.out.println("hha size nta3 la liste ==> "+dayDetails.size());
        for (DayDetail dayDetail : dayDetails) {
            Detail dd = detailService.findByWording(dayDetail.getDetail().getWording());
            dayDetail.setDetail(dd);
            DayDetail dayDetail1 = dayDetailService.createDayDetail(dayDetail);
            day.getDayDetails().add(dayDetail1);
            pan += dd.getPan();
            hn.setHour(hn.getHour() + dd.getHn().getHour());
            hn.setMinute(hn.getMinute() + dd.getHn().getMinute());
            he.setHour(he.getHour() + dd.getHe().getHour());
            he.setMinute(he.getMinute() + dd.getHe().getMinute());
        }
        day.setHn(hn);
        day.setHe(he);
        day.setPan(pan);
        dayDao.save(day);
        return day;
    }

    @Override
    public List<Day> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(Integer matricule, int year, int month) {
        Employee employee=employeeService.findByMatricule(matricule);
        if(employee==null){
            return null;
        }
        else{
        WorkDetail workDetail =workDetailService.findByEmployeeMatriculeAndWorkDetailDate(matricule, year,month);
        if (workDetail == null) {
            return null;
        } else {
            return workDetail.getDays();
        }
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
            if (theDay == null) {
                return null;
            } else {
                return theDay;
            }

        }

    }

    @Override
    public int createVacation(Vacation vacation) {
        int res = 0;
        Employee emp = employeeService.findByMatricule(vacation.getEmployee().getMatricule());
        LocalDate ldS = DateUtil.fromDate(vacation.getStartingDate());
        LocalDate ldE = DateUtil.fromDate(vacation.getEndingDate());
        System.out.println(ldE);
        System.out.println(ldS);
        System.out.println(vacation.getStartingDate());
        if (emp == null) {
            return res = -1;
        } else if (vacation.getType().equals("C.M") || vacation.getType().equals("C.AT") || vacation.getType().equals("C.EXCEP")) {
            List<LocalDate> daysVacation = betweenDate.between(ldS, ldE);
            for (LocalDate ld : daysVacation) {

                Day day = findByEmployeeMatriculeAndDateOfTheDay(emp.getMatricule(), DateUtil.toDate(ld));
                vacation.setEmployee(emp);
                day.setVacation(vacation);
                vacationService.saveVacation(vacation);
            }
            return res = 1;

        } else if (vacation.getType().equals("C.R")) {
            List<LocalDate> daysVacationWithoutSunday = betweenDate.withoutSunday(ldS, ldE);
            for (LocalDate ld : daysVacationWithoutSunday) {
                System.out.println(ld);
                Day day = findByEmployeeMatriculeAndDateOfTheDay(emp.getMatricule(), DateUtil.toDate(ld));
                vacation.setEmployee(emp);
                day.setVacation(vacation);
                vacationService.saveVacation(vacation);
            }
            return res = 2;
        }
        return res;
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

    @Override
    public List<Day> findByDateOfTheWork(Date dateOfTheDay) {
        LocalDate localDate=DateUtil.fromDate(dateOfTheDay);
        LocalDate checkLocalDate=LocalDate.of(localDate.getYear(),localDate.getMonth(),1);
        Date tmpDate=DateUtil.toDate(checkLocalDate);
     List<Day> listDay=new ArrayList<>();
        for (Work work : workService.findByWorkDetailWorkDetailDate(tmpDate)) {
     List<Day> daysOfWork=work.getWorkDetail().getDays();
            for (Day day : daysOfWork) {
                if(day.getDayDate().compareTo(dateOfTheDay)==0){
                listDay.add(day);
                }
            }
        }
        return listDay;
    }
}
