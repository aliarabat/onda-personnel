/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.dao.DayDao;
import com.onda.personnel.dao.DayDetailDao;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.personnel.dao.ReplacementDao;
import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Replacement;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.DetailService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.ReplacementService;
import org.springframework.data.domain.Sort;

/**
 * @author JaafarDiyaou
 */
@Service
public class ReplacementServiceImpl implements ReplacementService {

    @Autowired
    ReplacementDao replacementDao;

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
    public List<Replacement> findByOriginalEmployeeMatriculeAndReplacementDate(Integer matricule, Date replacementDate) {
        return replacementDao.findByOriginalEmployeeMatriculeAndReplacementDate(matricule, replacementDate);
    }

    @Override
    public Replacement createReplacement(Integer matricule, Integer matricule1, String wording, Replacement replacement) {
        Employee employee = employeeService.findByMatricule(matricule);
        Detail detail = detailService.findByWording(wording);
        Employee employee2 = employeeService.findByMatricule(matricule1);
        if (employee == null || employee2 == null) {
            return null;
        } else {

            if (detail != null) {
                replacement.setOriginalEmployee(employee);
                replacement.setReplacedEmpolyee(employee2);
                replacement.setDetail(detail);
                replacementDao.save(replacement);
                return replacement;
            } else {
                return null;
            }
        }

    }

    @Override
    public List<Replacement> findAll() {
        return replacementDao.findAll(Sort.by("replacementDate"));
    }

    public ReplacementDao getReplacementDao() {
        return replacementDao;
    }

    public void setReplacementDao(ReplacementDao replacementDao) {
        this.replacementDao = replacementDao;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
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

}
