/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.dao.DayDao;
import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.dao.SkipDao;
import com.onda.personnel.model.Day;
import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Replacement;
import com.onda.personnel.model.Skip;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.DetailService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.SkipService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JaafarDiyaou
 */
@Service
public class SkipServiceImpl implements SkipService {
    @Autowired
    SkipDao skipDao;
    @Autowired
    EmployeeService employeeService;
    
    
    @Autowired
    DetailService detailService;

    @Autowired
    DayDetailDao dayDetailDao;
    @Autowired
    DayService dayService;

    @Autowired
    DayDao dayDao;

    @Override
    public List<Skip> findByEmployeeMatriculeAndSkipDate(Integer matricule, Date skipDate) {
        return skipDao.findByEmployeeMatriculeAndSkipDate(matricule, skipDate);
    }

    @Override
    public Skip createSkip(Integer matricule,String wording, Skip skip) {
        Employee employee = employeeService.findByMatricule(matricule);
        if (employee == null) {
            return null;
        } else {
            Detail detail=detailService.findByWording(wording);
            if(detail!=null){
            skip.setEmployee(employee);
            skip.setDetail(detail);
            skipDao.save(skip);
            return skip ;
            }
            else return null;
        }
    }


    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public SkipDao getSkipDao() {
        return skipDao;
    }

    public void setSkipDao(SkipDao skipDao) {
        this.skipDao = skipDao;
    }

    public DetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

    public DayDetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DayDetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }

    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

    public DayDao getDayDao() {
        return dayDao;
    }

    public void setDayDao(DayDao dayDao) {
        this.dayDao = dayDao;
    }
    
    
    

 @Override
    public int updateSkip(DayDetail dayDetail) {
        DayDetail dayDetail1 = dayDetailDao.getOne(dayDetail.getId());
        if (dayDetail1 == null) {
            return -3;
        } else {
            Skip skip1 = skipDao.getOne(dayDetail1.getSkip().getId());
            Skip skip = dayDetail.getSkip();
            if (skip1 == null || skip == null) {
                return -1;
            } else {
                Day day1 = dayService.findByEmployeeMatriculeAndDateOfTheDay(skip1.getEmployee().getMatricule(), skip1.getSkipDate());
                Day day = dayService.findByEmployeeMatriculeAndDateOfTheDay(skip.getEmployee().getMatricule(), skip.getSkipDate());
                System.out.println("hahowaaaaaaa" + day);
                System.out.println("hahowaaaaaaa" + day1);
                if (day == null || day1 == null) {
                    return -2;
                } else if (day.getVacation() != null) {
                    return -4;
                } else if (day.getId().compareTo(day1.getId()) == 0) {
                    DayDetail checkDayDetail1 = null;
                    List<DayDetail> listDayDetails1 = day1.getDayDetails();

                    for (DayDetail listDayDetail1 : listDayDetails1) {
                        if (listDayDetail1.getDetail() == null) {
                            if (listDayDetail1.getSkip().getDetail().getWording().equals(dayDetail.getSkip().getDetail().getWording())) {
                                checkDayDetail1 = dayDetailDao.getOne(listDayDetail1.getId());
                                break;
                            }
                        } else {
                            if (listDayDetail1.getDetail().getWording().equals(dayDetail.getSkip().getDetail().getWording())) {
                                checkDayDetail1 = dayDetailDao.getOne(listDayDetail1.getId());
                                break;
                            }
                        }
                    }
                    if (checkDayDetail1 != null) {
                        if (checkDayDetail1.getReplacement() == null && checkDayDetail1.getSkip() == null && checkDayDetail1.getMission() == null) {
                            skip1.setEmployee(dayDetail.getSkip().getEmployee());
                            //skip1.setReference(dayDetail.getSkip().getReference());
                            skip1.setType(dayDetail.getSkip().getType());
                            skip1.setSkipDate(dayDetail.getSkip().getSkipDate());
                            skip1.setDetail(dayDetail.getDetail());
                            skipDao.save(skip1);
                            checkDayDetail1.setSkip(skip1);
                            dayDetailDao.save(checkDayDetail1);
                            dayDetail1.setSkip(null);
                            dayDetailDao.save(dayDetail1);
                            return 1;
                        } else {
                            return -7;
                        }
                    } else {
                         skip1.setEmployee(dayDetail.getSkip().getEmployee());
                            //skip1.setReference(dayDetail.getSkip().getReference());
                            skip1.setType(dayDetail.getSkip().getType());
                            skip1.setSkipDate(dayDetail.getSkip().getSkipDate());
                            skip1.setDetail(dayDetail.getDetail());
                            skipDao.save(skip1);
                        checkDayDetail1 = new DayDetail();
                        checkDayDetail1.setDetail(null);
                        checkDayDetail1.setSkip(skip1);
                        dayDetailDao.save(checkDayDetail1);
                        day1.getDayDetails().add(checkDayDetail1);
                        dayDao.save(day1);

                        dayDetail1.setSkip(null);
                        dayDetailDao.save(dayDetail1);

                        return 7;
                    }

                } else {
                    DayDetail checkDayDetail = null;
                    List<DayDetail> listDayDetails = day.getDayDetails();
                    for (DayDetail listDayDetail : listDayDetails) {
                        if (listDayDetail.getDetail() == null) {
                            if (listDayDetail.getSkip().getDetail().getWording().equals(dayDetail.getSkip().getDetail().getWording())) {
                                checkDayDetail = dayDetailDao.getOne(listDayDetail.getId());
                                break;
                            }
                        } else {
                            if (listDayDetail.getDetail().getWording().equals(dayDetail.getSkip().getDetail().getWording())) {
                                checkDayDetail = dayDetailDao.getOne(listDayDetail.getId());
                                break;
                            }
                        }
                    }
                    if (checkDayDetail == null) {
                        skip1.setEmployee(dayDetail.getSkip().getEmployee());
                            //skip1.setReference(dayDetail.getSkip().getReference());
                            skip1.setType(dayDetail.getSkip().getType());
                            skip1.setSkipDate(dayDetail.getSkip().getSkipDate());
                            skip1.setDetail(dayDetail.getDetail());
                            skipDao.save(skip1);
                        checkDayDetail = new DayDetail();
                        checkDayDetail.setDetail(null);
                        checkDayDetail.setSkip(skip1);
                        dayDetailDao.save(checkDayDetail);
                        day.getDayDetails().add(checkDayDetail);
                        dayDao.save(day);
                        dayDetail1.setSkip(null);
                        dayDetailDao.save(dayDetail1);

                        return 2;

                    } else {

                        if (checkDayDetail.getReplacement() == null && checkDayDetail.getSkip() == null && checkDayDetail.getMission() == null) {
                            skip1.setEmployee(dayDetail.getSkip().getEmployee());
                            //skip1.setReference(dayDetail.getSkip().getReference());
                            skip1.setType(dayDetail.getSkip().getType());
                            skip1.setSkipDate(dayDetail.getSkip().getSkipDate());
                            skip1.setDetail(dayDetail.getDetail());
                            skipDao.save(skip1);
                            checkDayDetail.setSkip(skip1);
                            dayDetail1.setSkip(null);
                            dayDetailDao.save(dayDetail1);
                            return 3;
                        } else {
                            return -6;
                        }

                    }
                }
            }
        }
    }
}
