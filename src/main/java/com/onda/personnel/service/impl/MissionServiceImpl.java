/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Mission;
import com.onda.personnel.dao.MissionDao;
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
    EmployeeService employeeService;

    @Override
    public Mission createMisssion(Integer matricule, Mission mission) {

        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return null;
        } else {
            mission.setEmployee(employee);
            missionDao.save(mission);
            return mission;
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

}
