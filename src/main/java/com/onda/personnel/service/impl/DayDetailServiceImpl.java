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
import com.onda.personnel.bean.Replacement;
import com.onda.personnel.bean.Skip;
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
import com.onda.personnel.service.ReplacementService;
import com.onda.personnel.service.SkipService;
import com.onda.personnel.service.WorkService;
import java.time.LocalDate;
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
    private SkipService skipService;
    @Autowired
    private ReplacementService replacementService;
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
    public int updateDayDetailMission(Integer matricule, String wordingDetail, Mission mission) {
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
                WorkDetail workDetail = workDetailDao.getOne(theWork.getWorkDetail().getId());

                List<Day> listDay = workDetail.getDays();

                Day theDay = new Day();
                for (Day day : listDay) {
                    if (day.getDayDate().compareTo(mission.getStartingDate()) == 0) {
                        theDay = dayDao.getOne(day.getId());
                    }
                }
                if(theDay==null){
                    return -6;}
                else{
                if (theDay.getVacation() != null) {
                    return -3;
                } else {

                    List<DayDetail> listdayDetails = theDay.getDayDetails();

                    DayDetail thedayDetail = new DayDetail();
                    Detail detail = detailService.findByWording(wordingDetail);

                    for (DayDetail dayDetail : listdayDetails) {
                        if(dayDetail.getDetail()!=null){
                        if (dayDetail.getDetail().getWording().equals(detail.getWording())) {
                            thedayDetail = dayDetailDao.getOne(dayDetail.getId());

                        }

                    }
                    }

                    if (thedayDetail.getReplacement() != null || thedayDetail.getSkip() != null || thedayDetail.getMission() != null) {
                        return -2;
                    } else {
                        Mission checkMission = missionService.createMisssion(matricule, mission);
                        if (checkMission != null) {
                            thedayDetail.setMission(checkMission);
                            dayDetailDao.save(thedayDetail);

                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            }
            }

        }
    }

    @Override
    public int updateDayDetailSkip(Integer matricule, String wordingDetail, Skip skip) {
        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return -5;
        } else {
            Date tmpDate = skip.getSkipDate();
            LocalDate tmpLocalDate = DateUtil.fromDate(tmpDate);
            LocalDate checkLocalDate = tmpLocalDate;
            tmpLocalDate = LocalDate.of(checkLocalDate.getYear(), checkLocalDate.getMonth(), 1);
            tmpDate = DateUtil.toDate(tmpLocalDate);
            Work theWork = workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule, tmpDate);
            if (theWork == null) {
                return -4;
            } else {
                WorkDetail workDetail = workDetailDao.getOne(theWork.getWorkDetail().getId());

                List<Day> listDay = workDetail.getDays();

                Day theDay = new Day();
                for (Day day : listDay) {
                    if (day.getDayDate().compareTo(skip.getSkipDate()) == 0) {
                        theDay = dayDao.getOne(day.getId());
                    }
                }
                if(theDay==null){
                    return -6;
                }
                else{

                if (theDay.getVacation() != null) {
                    return -3;
                } else {

                    List<DayDetail> listdayDetails = theDay.getDayDetails();

                    DayDetail thedayDetail = new DayDetail();
                    Detail detail = detailService.findByWording(wordingDetail);

                    for (DayDetail dayDetail : listdayDetails) {
                        if(dayDetail.getDetail()!=null){

                        if (dayDetail.getDetail().getWording().equals(detail.getWording())) {
                            thedayDetail = dayDetailDao.getOne(dayDetail.getId());

                        }
                        }
                    }

                    if (thedayDetail.getReplacement() != null || thedayDetail.getSkip() != null || thedayDetail.getMission() != null) {
                        return -2;
                    } else {
                        Skip checkSkip = skipService.createSkip(matricule, skip);
                        if (checkSkip != null) {
                            thedayDetail.setSkip(checkSkip);
                            dayDetailDao.save(thedayDetail);

                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            }
            }

        }
    }

    @Override
    public int updateDayDetailReplacement(Integer matricule, Integer matricule1, String wordingDetail, Replacement replacement) {
        Employee employee = employeeService.findByMatricule(matricule);
        Employee employee1 = employeeService.findByMatricule(matricule1);
        if (employee == null || employee1 == null) {
            return -6;
        } else {
            Date tmpDate = replacement.getReplacementDate();
            LocalDate tmpLocalDate = DateUtil.fromDate(tmpDate);
            LocalDate checkLocalDate = tmpLocalDate;
            tmpLocalDate = LocalDate.of(checkLocalDate.getYear(), checkLocalDate.getMonth(), 1);
            tmpDate = DateUtil.toDate(tmpLocalDate);
            Work theWork = workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule, tmpDate);
            Work theWork1 = workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule1, tmpDate);
            if (theWork == null || theWork1 == null) {
                return -5;
            } else {
                WorkDetail workDetail = workDetailDao.getOne(theWork.getWorkDetail().getId());
                WorkDetail workDetail1 = workDetailDao.getOne(theWork1.getWorkDetail().getId());

                List<Day> listDay = workDetail.getDays();
                List<Day> listDay1 = workDetail1.getDays();

                Day theDay = new Day();
                Day theDay1 = new Day();
                for (Day day : listDay) {
                    if (day.getDayDate().compareTo(replacement.getReplacementDate()) == 0) {
                        theDay = dayDao.getOne(day.getId());
                    }

                }

                for (Day day : listDay1) {
                    if (day.getDayDate().compareTo(replacement.getReplacementDate()) == 0) {
                        theDay1 = dayDao.getOne(day.getId());
                    }

                }

                if (theDay == null || theDay1 == null) {
                    return -4;
                } else {

                    if (theDay.getVacation() != null || theDay1.getVacation() != null) {
                        return -3;
                    } else {

                        List<DayDetail> listdayDetails = theDay.getDayDetails();
                        List<DayDetail> listdayDetails1 = theDay1.getDayDetails();

                        DayDetail thedayDetail = new DayDetail();
                        DayDetail thedayDetail1 = new DayDetail();
                        Detail detail = detailService.findByWording(wordingDetail);

                        for (DayDetail dayDetail : listdayDetails) {
                                                     if(dayDetail.getDetail()!=null){
                            if (dayDetail.getDetail().getWording().equals(detail.getWording())) {
                                thedayDetail = dayDetailDao.getOne(dayDetail.getId());

                            }

                        }
                        }
                        int i = 0;

                        for (DayDetail dayDetail : listdayDetails1) {
                        if(dayDetail.getDetail()!=null){
                            if (dayDetail.getDetail().getWording().equals(detail.getWording())) {

                                i = 0;
                                break;
                            } else {

                                i = 1;
//                                
                            }

                        }
                        }
                        if (i == 0) {
                            return -2;
                        } else {

                            if (thedayDetail.getReplacement() != null || thedayDetail.getSkip() != null || thedayDetail.getMission() != null) {
                                return -1;
                            } else {
                                Replacement checkReplacement = replacementService.createReplacement(matricule, matricule1, detail.getWording(), replacement);
                                if (checkReplacement != null) {
                                    thedayDetail.setReplacement(checkReplacement);
                                    thedayDetail1.setReplacement(replacement);
                                    thedayDetail1.setDetail(null);
                                    dayDetailDao.save(thedayDetail1);
                                    listdayDetails1.add(thedayDetail1);
                                    dayDetailDao.save(thedayDetail);

                                    return 1;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
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

    public SkipService getSkipService() {
        return skipService;
    }

    public void setSkipService(SkipService skipService) {
        this.skipService = skipService;
    }

}
