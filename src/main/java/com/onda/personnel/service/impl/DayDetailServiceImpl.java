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
import com.onda.personnel.bean.Mission;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.dao.WorkDetailDao;
import com.onda.personnel.service.DayDetailService;
import com.onda.personnel.service.DetailService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.MissionService;
import com.onda.personnel.service.WorkService;
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
public class DayDetailServiceImpl implements DayDetailService {

    @Autowired
    private DayDetailDao dayDetailDao;
    @Autowired
    private DayDao dayDao;
    @Autowired
    private WorkDetailDao workDetailDao;
    @Autowired
    private WorkDao workDao;
    @Autowired
    private WorkService workService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MissionService missionService;

    @Autowired
    private DetailService detailService;

    @Override
    public DayDetail createDayDetail(DayDetail dayDetail) {
        return dayDetailDao.save(dayDetail);
    }

    @Override
    public int updateDayDetailMission(Integer matricule,  String wordingDetail, Mission mission) {
        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return -5;
        } else {
            Date tmpDate = mission.getStartingDate();
            LocalDate tmpLocalDate = DateUtil.fromDate(tmpDate);
            LocalDate checkLocalDate = tmpLocalDate;
            tmpLocalDate = LocalDate.of(checkLocalDate.getYear(), checkLocalDate.getMonth(), 1);
            tmpDate = DateUtil.toDate(tmpLocalDate);
            Work theWork = workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule, tmpDate);
            if (theWork == null) {
                return -4;
            } else {
                WorkDetail workDetail = theWork.getWorkDetail();
                List<Day> listDay = workDetail.getDays();
                Day theDay = new Day();
                for (Day day : listDay) {
                    if (day.getDayDate().compareTo(mission.getStartingDate()) == 0) {
                        theDay = day;
                    }
                }
                List<DayDetail> listdayDetails = theDay.getDayDetails();
                DayDetail thedayDetail = new DayDetail();
                Detail detail = detailService.findByWording(wordingDetail);
                for (DayDetail dayDetail : listdayDetails) {
                    if (dayDetail.getDetail().getId()==detail.getId()) {
                        thedayDetail = dayDetail;
                    }
                }
                Mission checkMission= missionService.createMisssion(matricule,mission);
                if (checkMission != null) {
                    thedayDetail.setMission(checkMission);
                    dayDetailDao.save(thedayDetail);
                    List<DayDetail> modifyDayDetails = listdayDetails;
                    modifyDayDetails.remove(thedayDetail);
    Integer panDay=0;
    Integer hnDay=0;
    Integer heDay=0;
    
    
    
    for (DayDetail dayDetail : modifyDayDetails) {
        panDay+=dayDetail.getDetail().getPan();
        hnDay+=dayDetail.getDetail().getHn();
        heDay+=dayDetail.getDetail().getHe();
                    
                }
    theDay.setPan(panDay);
    theDay.setHn(hnDay);
    theDay.setHe(heDay);
    
        dayDao.save (theDay);
    
    Integer panMonth=0;
    Integer hnMonth=0;
    Integer heMonth=0;
    
    
    for (Day day :listDay ) {
        panMonth+=day.getPan();
        hnMonth+=day.getHn();
        heMonth+=day.getHe();
                    
                }
    workDetail.setHjf(heMonth);
    workDetail.setHn(hnMonth);
    workDetail.setPan(panMonth);
    


    workDetailDao.save (workDetail);

    workDao.save (theWork);


return 1;
                } else {
                    return -1;
                }

            }
        }
    }

    public DayDetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DayDetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public MissionService getMissionService() {
        return missionService;
    }

    public void setMissionService(MissionService missionService) {
        this.missionService = missionService;
    }

    public DetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

    public DayDao getDayDao() {
        return dayDao;
    }

    public void setDayDao(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    public WorkDetailDao getWorkDetailDao() {
        return workDetailDao;
    }

    public void setWorkDetailDao(WorkDetailDao workDetailDao) {
        this.workDetailDao = workDetailDao;
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

}
