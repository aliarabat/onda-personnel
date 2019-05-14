/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.dao.DayDao;
import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.dao.MissionDao;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Mission;
import com.onda.personnel.model.Day;
import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Detail;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.DetailService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.MissionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AMINE
 */
@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    MissionDao missionDao;
    @Autowired
    DayDetailDao dayDetailDao;
    @Autowired
    DayService dayService;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    DetailService detailService;

    @Autowired
    DayDao dayDao;

    @Override
    public Mission createMisssion(Integer matricule, Mission mission) {

        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return null;
        } else {
//            Detail detail = detailService.findByWording(wording);
//            if (detail == null) {
//                return null;
//            } else {
            mission.setEmployee(employee);
            missionDao.save(mission);
            return mission;
            //}
        }
    }

    @Override
    public List<Mission> findByEmployeeMatriculeAndStartingDate(Integer matricule, Date startingDate) {
        return missionDao.findByEmployeeMatriculeAndStartingDate(matricule, startingDate);
    }

    public MissionDao getMissionDao() {
        return missionDao;
    }

    public void setMissionDao(MissionDao missionDao) {
        this.missionDao = missionDao;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

    public DayDetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DayDetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }

    public DayDao getDayDao() {
        return dayDao;
    }

    public void setDayDao(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    public DetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

    @Override
    public int updateMission(DayDetail dayDetail) {
        DayDetail dayDetail1 = dayDetailDao.getOne(dayDetail.getId());
        if (dayDetail1 == null) {
            return -3;
        } else {
            Mission mission1 = missionDao.getOne(dayDetail1.getMission().getId());
            Mission mission = dayDetail.getMission();
            if (mission1 == null || mission == null) {
                return -1;
            } else {
                Day day1 = dayService.findByEmployeeMatriculeAndDateOfTheDay(mission1.getEmployee().getMatricule(), mission1.getStartingDate());
                Day day = dayService.findByEmployeeMatriculeAndDateOfTheDay(mission.getEmployee().getMatricule(), mission.getStartingDate());
//                System.out.println("hahowaaaaaaa" + day);
//                System.out.println("hahowaaaaaaa" + day1);
                if (day == null || day1 == null) {
                    return -2;
                } else if (day.getVacation() != null) {
                    return -4;
                } else {

                    mission1.setEmployee(dayDetail.getMission().getEmployee());
                    mission1.setReference(dayDetail.getMission().getReference());
                    mission1.setType(dayDetail.getMission().getType());
                    mission1.setStartingDate(dayDetail.getMission().getStartingDate());
                    mission1.setEndingTime(dayDetail.getMission().getEndingTime());
                    mission1.setStartingTime(dayDetail.getMission().getStartingTime());
                    missionDao.save(mission1);
                    DayDetail checkDayDetail = new DayDetail();
                    checkDayDetail.setDetail(dayDetail1.getDetail());
                    checkDayDetail.setMission(mission1);
                    checkDayDetail.setReplacement(dayDetail1.getReplacement());
                    checkDayDetail.setSkip(dayDetail1.getSkip());

                    dayDetailDao.delete(dayDetail1);
                    day1.getDayDetails().remove(dayDetail1);
                    dayDao.save(day1);
                    dayDetailDao.save(checkDayDetail);
                    day.getDayDetails().add(checkDayDetail);
                    dayDao.save(day);

                    return 1;

                }
            }
        }
    }

}
