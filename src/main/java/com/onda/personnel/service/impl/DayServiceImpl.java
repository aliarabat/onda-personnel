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
import com.onda.personnel.bean.Timing;
import com.onda.personnel.bean.Vacation;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.PeriodUtil;
import com.onda.personnel.common.util.betweenDate;
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
import com.onda.personnel.service.VacationService;
import java.sql.Array;
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
    private WorkDetailSevice workDetailSevice;

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
        } else {
            List<Day> daysSaved = new ArrayList<>();
            for (Day day : days) {
                daysSaved.add(setDayInfos(day, day.getDayDetails()));
            }
            //instanciation of workDetail found and newWorkDetail
            workDetailSevice.createWorkDetail(emp, daysSaved);
            return 1;
        }
    }

    private Day setDayInfos(Day day, List<DayDetail> dayDetails) {
        Integer pan = 0;
        Timing hn = new Timing(0, 0);
        Timing he = new Timing(0, 0);
        for (DayDetail dayDetail : dayDetails) {
            Detail dd = detailService.findByWording(dayDetail.getDetail().getWording());
            dayDetail.setDetail(dd);
            DayDetail dayDetail1 = dayDetailService.createDayDetail(dayDetail);
            day.getDayDetails().add(dayDetail1);
            pan += dd.getPan();
//            hn += dd.getHn();
//            he += dd.getHe();
        }
        day.setHn(hn);
        day.setHe(he);
        day.setPan(pan);
        dayDao.save(day);
        return day;
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
        }
        else if (vacation.getType().equals("C.M") || vacation.getType().equals("C.AT") || vacation.getType().equals("C.EXCEP")) {
            List<LocalDate> daysVacation = betweenDate.between(ldS, ldE);
            for (LocalDate ld : daysVacation) {
                Day day = dayDao.findByDayDate(DateUtil.toDate(ld));
                vacation.setEmployee(emp);
                day.setVacation(vacation);
                vacationService.saveVacation(vacation);
            }
            return res = 1;
            
        } else if (vacation.getType().equals("C.R")) {
            List<LocalDate> daysVacationWithoutSunday = betweenDate.withoutSunday(ldS, ldE);
            for (LocalDate ld : daysVacationWithoutSunday) {
                System.out.println(ld);
                Day day = dayDao.findByDayDate(DateUtil.toDate(ld));
                vacation.setEmployee(emp);
                day.setVacation(vacation);
                vacationService.saveVacation(vacation);
            }
            return res = 2;
        }
        return res;
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
