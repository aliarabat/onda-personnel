/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.util.DateUtil;
import com.onda.personnel.util.PeriodUtil;
import com.onda.personnel.util.betweenDate;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.model.*;
import com.onda.personnel.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
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

    @Autowired
    private HolidayService holidayService;

    @Override
    public int createDay(Integer matricule, List<Day> days) {
        Employee emp = employeeService.findByMatricule(matricule);
        if (emp == null) {
            return -1;
        } else if (days == null || days.isEmpty()) {
            return -2;
        } else if (days.size() > 7 || days.size() < 6) {
            return -3;
        } else {
            workDetailService.createWorkDetail(emp, days);
            return 1;
        }
    }

    @Override
    public Day setDayInfos(List<DayDetail> dayDetails, Date ld) {
        Day day = new Day();
        int pan = 0, minutesHnWorked = 0, minutesHeWorked = 0, hoursHnWorked = 0, hoursHeWorked = 0;
        day.setDayDate(ld);
        for (DayDetail dayDetail : dayDetails) {
            Detail dd = new Detail();
            if (dayDetail.getDetail().getWording().equals("R")) {
                dd = detailService.findTopByWording("R");
            } else {
                dd = detailService.findByWording(dayDetail.getDetail().getWording());
            }
            dayDetail.setDetail(dd);
            day.getDayDetails().add(dayDetailService.createDayDetail(dayDetail));
            if (!dd.getWording().equals("R")) {
                pan += dd.getPan();
                hoursHnWorked += dd.getHn().getHour();
                minutesHnWorked += dd.getHn().getMinute();
                hoursHeWorked += dd.getHe().getHour();
                minutesHeWorked += dd.getHe().getMinute();
                PeriodUtil.minutesToHour(hoursHnWorked, minutesHnWorked, hoursHeWorked, minutesHeWorked);
            }
        }
        day.setHn(new Timing(hoursHnWorked, minutesHnWorked));
        day.setHe(new Timing(hoursHeWorked, minutesHeWorked));
        day.setPan(pan);
        Holiday hol = holidayService.findByStartingDateLessThanEqualAndEndingDateGreaterThanEqual(ld, ld);

        if (hol != null) {
            day.setReference(hol.getReference());
        }
        dayDao.save(day);
        return day;
    }

    @Override
    public List<Day> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(Integer matricule, int year, int month) {
        List<Work> work = workService.findByEmployeeMatriculeAndMonthAndYear(matricule, year, month);
        if (work == null) {
            return null;
        } else {
            //return work.getWorkDetail().getDays();
            return null;
        }
    }

    @Override
    public Day findByEmployeeMatriculeAndDateOfTheDay(Integer matricule, Date dayDate) {
        LocalDate localDate = DateUtil.fromDate(dayDate);
        LocalDate checklocalDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        Date tmpDate = DateUtil.toDate(checklocalDate);
        Work work = workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, tmpDate);
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
    public int createVacation(Vacation vacation, Integer matricule) {
        int res = 0;
        Employee emp = employeeService.findByMatricule(matricule);
        LocalDate ldS = DateUtil.fromDate(vacation.getStartingDate());
        LocalDate ldE = DateUtil.fromDate(vacation.getEndingDate());

        if (emp == null) {
            return res = -1;
        } else if (vacation.getType().equals("C.M") || vacation.getType().equals("C.AT") || vacation.getType().equals("C.EXCEP")) {
            List<LocalDate> daysVacation = betweenDate.between(ldS, ldE);
            for (LocalDate ld : daysVacation) {
                Day day = findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(ld));
                vacation.setEmployee(emp);
                day.setVacation(vacation);
                vacationService.saveVacation(vacation);

            }
            return res = 1;

        } else if (vacation.getType().equals("C.R")) {
            List<LocalDate> daysVacationWithoutSunday = betweenDate.noSundays(ldS, ldE);
            for (LocalDate ld : daysVacationWithoutSunday) {
                System.out.println(ld);
                Day day = findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(ld));
                vacation.setEmployee(emp);
                day.setVacation(vacation);
                vacationService.saveVacation(vacation);

            }
            return res = 2;
        }
        return res;
    }

    @Override
    public int updateVacation(Vacation vacation, Integer matricule) {
        int res = 0;
        Vacation oldVacation = vacationService.getVacationByID(vacation.getId());
        Employee emp = employeeService.findByMatricule(matricule);

        LocalDate ldS = DateUtil.fromDate(vacation.getStartingDate());
        LocalDate ldE = DateUtil.fromDate(vacation.getEndingDate());

        LocalDate ldSOld = DateUtil.fromDate(oldVacation.getStartingDate());
        LocalDate ldEOld = DateUtil.fromDate(oldVacation.getEndingDate());

        if (emp == null) {
            return res = -1;

        } else if (vacation.getType().equals("C.M") || vacation.getType().equals("C.AT") || vacation.getType().equals("C.EXCEP")) {
            List<LocalDate> daysVacation = betweenDate.between(ldS, ldE);
            List<LocalDate> daysVacationOld = betweenDate.between(ldSOld, ldEOld);
            daysVacation.removeAll(daysVacationOld);
            List<LocalDate> daysVacation2 = betweenDate.between(ldS, ldE);
            List<LocalDate> daysVacationOld2 = betweenDate.between(ldSOld, ldEOld);
            daysVacationOld2.removeAll(daysVacation2);
            oldVacation.setEmployee(emp);
            oldVacation.setEndingDate(vacation.getEndingDate());
            oldVacation.setStartingDate(vacation.getStartingDate());
            oldVacation.setType(vacation.getType());
            for (LocalDate localDate : daysVacation) {
                Day day = findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(localDate));
                vacationService.saveVacation(oldVacation);
                day.setVacation(oldVacation);
                dayDao.save(day);
            }
            for (LocalDate ld : daysVacationOld2) {
                Day Noday = findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(ld));
                Noday.setVacation(null);
                dayDao.save(Noday);
            }

            return res = 1;

        } else if (vacation.getType().equals("C.R")) {
            List<LocalDate> daysVacation = betweenDate.noSundays(ldS, ldE);
            List<LocalDate> daysVacationOld = betweenDate.noSundays(ldSOld, ldEOld);
            daysVacation.removeAll(daysVacationOld);
            List<LocalDate> daysVacation2 = betweenDate.noSundays(ldS, ldE);
            List<LocalDate> daysVacationOld2 = betweenDate.noSundays(ldSOld, ldEOld);
            daysVacationOld2.removeAll(daysVacation2);
            oldVacation.setEmployee(emp);
            oldVacation.setEndingDate(vacation.getEndingDate());
            oldVacation.setStartingDate(vacation.getStartingDate());
            oldVacation.setType(vacation.getType());
            for (LocalDate localDate : daysVacation) {
                Day day = findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(localDate));
                vacationService.saveVacation(oldVacation);
                day.setVacation(oldVacation);
                dayDao.save(day);
            }
            for (LocalDate ld : daysVacationOld2) {
                Day Noday = findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(ld));
                Noday.setVacation(null);
                dayDao.save(Noday);
            }
            return res = 2;
        }
        return res;
    }

    @Override
    public List<Day> findByDayDate(Date dayDate) {
        return dayDao.findByDayDate(dayDate);
    }

    @Override
    public List<Day> findByVacationId(Long id) {
        return dayDao.findByVacationId(id);
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
        LocalDate localDate = DateUtil.fromDate(dateOfTheDay);
        LocalDate checkLocalDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        Date tmpDate = DateUtil.toDate(checkLocalDate);
        List<Day> listDay = new ArrayList<>();
        for (Work work : workService.findByWorkDetailWorkDetailDate(tmpDate)) {
            List<Day> daysOfWork = work.getWorkDetail().getDays();
            for (Day day : daysOfWork) {
                if (day.getDayDate().compareTo(dateOfTheDay) == 0) {
                    listDay.add(day);
                }
            }
        }
        return listDay;
    }

    public VacationService getVacationService() {
        return vacationService;
    }

    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
}
