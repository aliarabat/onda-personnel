/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.dao.DayDao;
import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.dao.MissionDao;
import com.onda.personnel.dao.ReplacementDao;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.dao.WorkDetailDao;
import com.onda.personnel.model.Day;
import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Mission;
import com.onda.personnel.model.Replacement;
import com.onda.personnel.model.Skip;
import com.onda.personnel.model.Work;
import com.onda.personnel.model.WorkDetail;
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
    private ReplacementDao replacementDao;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MissionService missionService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private MissionDao missionDao;

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
            Work theWork = workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, tmpDate);
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
                if (theDay == null) {
                    return -6;
                } else {
                    if (theDay.getVacation() != null) {
                        return -3;
                    } else {

                        List<DayDetail> listdayDetails = theDay.getDayDetails();

                        DayDetail thedayDetail = new DayDetail();
                        Detail detail = detailService.findByWording(wordingDetail);

                        for (DayDetail dayDetail : listdayDetails) {
                            if (dayDetail.getDetail() != null) {
                                if (dayDetail.getDetail().getWording().equals(detail.getWording())) {
                                    thedayDetail = dayDetailDao.getOne(dayDetail.getId());

                                }

                            }
                        }

                        if (thedayDetail.getReplacement() != null || thedayDetail.getSkip() != null || thedayDetail.getMission() != null) {
                            return -2;
                        } else {
                            Mission checkMission = missionService.createMisssion(matricule, wordingDetail, mission);
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
            Work theWork = workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, tmpDate);
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
                if (theDay == null) {
                    return -6;
                } else {

                    if (theDay.getVacation() != null) {
                        return -3;
                    } else {

                        List<DayDetail> listdayDetails = theDay.getDayDetails();

                        DayDetail thedayDetail = new DayDetail();
                        Detail detail = detailService.findByWording(wordingDetail);

                        for (DayDetail dayDetail : listdayDetails) {
                            if (dayDetail.getDetail() != null) {

                                if (dayDetail.getDetail().getWording().equals(detail.getWording())) {
                                    thedayDetail = dayDetailDao.getOne(dayDetail.getId());

                                }
                            }
                        }

                        if (thedayDetail.getReplacement() != null || thedayDetail.getSkip() != null || thedayDetail.getMission() != null) {
                            return -2;
                        } else {
                            Skip checkSkip = skipService.createSkip(matricule, wordingDetail, skip);
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
            Work theWork = workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, tmpDate);
            Work theWork1 = workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule1, tmpDate);
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
                            if (dayDetail.getDetail() != null) {
                                if (dayDetail.getDetail().getWording().equals(detail.getWording())) {
                                    thedayDetail = dayDetailDao.getOne(dayDetail.getId());

                                }

                            }
                        }
                        int i = 0;

                        for (DayDetail dayDetail : listdayDetails1) {
                            if (dayDetail.getDetail() != null) {
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

    @Override
    public List<DayDetail> findAll() {
        return dayDetailDao.findAll();
    }

    @Override
    public List<DayDetail> findByMissionIsNotNull() {
        return dayDetailDao.findByMissionIsNotNull();
    }

    @Override
    public int updateDayDetailByDeletingMission(DayDetail dayDetail) {
        List<DayDetail> listdayDetails = findByMissionIsNotNull();
        int res = 0;
        DayDetail dayDetail1 = new DayDetail();
        for (DayDetail listdayDetail : listdayDetails) {
            if (listdayDetail.getId().compareTo(dayDetail.getId()) == 0) {
                dayDetail1 = listdayDetail;
                res = 1;
                break;
            }
        }
        if (res == 1) {
            Mission mission = dayDetail1.getMission();
            dayDetail1.setMission(null);

            missionDao.delete(mission);
            dayDetailDao.save(dayDetail1);

            return res;
        } else {
            return res;
        }
    }

    @Override
    public DayDetail findById(Long id) {
        return dayDetailDao.getOne(id);
    }

    @Override
    public List<DayDetail> findByReplacementIsNotNullAndDetailIsNotNull() {
        return dayDetailDao.findByReplacementIsNotNullAndDetailIsNotNull();
    }

    @Override
    public List<DayDetail> findByDetailIsNullAndSkipIsNullAndRepalcementIsNullAndMissionIsNull() {
        return dayDetailDao.findByDetailIsNullAndSkipIsNullAndReplacementIsNullAndMissionIsNull();
    }

    @Override
    public int deleteDayDetailWhereIsNull() {

        List<DayDetail> listDayDetails = findByDetailIsNullAndSkipIsNullAndRepalcementIsNullAndMissionIsNull();
        for (DayDetail listDayDetail : listDayDetails) {
            dayDetailDao.delete(listDayDetail);
        }
        return 1;
    }

    @Override
    public DayDetail findByReplacementIdAndDetailIsNull(Long id) {
        return dayDetailDao.findByReplacementIdAndDetailIsNull(id);
    }

    @Override
    public int updateDayDetailByDeletingReplacement(DayDetail dayDetail) {
        List<DayDetail> listdayDetails = findByReplacementIsNotNullAndDetailIsNotNull();
        int res = 0;
        DayDetail dayDetail1 = new DayDetail();
        for (DayDetail listdayDetail : listdayDetails) {
            if (listdayDetail.getId().compareTo(dayDetail.getId()) == 0) {
                dayDetail1 = listdayDetail;
                res = 1;
                break;
            }
        }

        if (res == 1) {
            Replacement replacement = dayDetail1.getReplacement();
            dayDetail1.setReplacement(null);
            DayDetail dayDetail2 = findByReplacementIdAndDetailIsNull(replacement.getId());
            dayDetail2.setReplacement(null);
            dayDetailDao.save(dayDetail1);
            dayDetailDao.save(dayDetail2);
            replacementDao.delete(replacement);
            deleteDayDetailWhereIsNull();
            return res;
        } else {
            return res;
        }
    }

    @Override
    public List<DayDetail> findBySkipIsNotNull() {
        return dayDetailDao.findBySkipIsNotNull();
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

    public ReplacementService getReplacementService() {
        return replacementService;
    }

    public void setReplacementService(ReplacementService replacementService) {
        this.replacementService = replacementService;
    }

    public MissionDao getMissionDao() {
        return missionDao;
    }

    public void setMissionDao(MissionDao missionDao) {
        this.missionDao = missionDao;
    }

    public ReplacementDao getReplacementDao() {
        return replacementDao;
    }

    public void setReplacementDao(ReplacementDao replacementDao) {
        this.replacementDao = replacementDao;
    }

    @Override
    public List<DayDetail> findBySkipId(Long id) {
        return dayDetailDao.findBySkipId(id);
    }

}
