/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.dao.VacationDao;
import com.onda.personnel.model.Day;
import com.onda.personnel.model.Vacation;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.VacationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class VacationServiceImpl implements VacationService {

    @Autowired
    private VacationDao vacationDao;
    @Autowired
    private DayService dayService;

    @Override
    public void saveVacation(Vacation vacation) {
        vacationDao.save(vacation);
    }

    @Override
    public int removeVacation(Long id) {
        Vacation vac = vacationDao.getOne(id);
        if (vac == null) {
            return -1;
        } else {
            List<Day> days = dayService.findByVacationId(id);
            for (Day day : days) {
                day.setVacation(null);
            }
            vacationDao.delete(vac);
            return 1;
        }
    }

    @Override
    public Vacation getVacationByID(Long id) {
        return vacationDao.getOne(id);
    }
    @Override
    public List<Vacation> findAllVacation() {
        return vacationDao.findAll();
    }

    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

    public VacationDao getVacationDao() {
        return vacationDao;
    }

    public void setVacationDao(VacationDao vacationDao) {
        this.vacationDao = vacationDao;
    }


}
