/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.dao.VacationDao;
import com.onda.personnel.model.Vacation;
import com.onda.personnel.service.VacationService;
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

    @Override
    public void saveVacation(Vacation vacation) {
        vacationDao.save(vacation);
    }

    public VacationDao getVacationDao() {
        return vacationDao;
    }

    public void setVacationDao(VacationDao vacationDao) {
        this.vacationDao = vacationDao;
    }

}
