/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.service.DayDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class DayDetailServiceImpl  implements DayDetailService{

    
    @Autowired
    private  DayDetailDao dayDetailDao;
    @Override
    public DayDetail createDayDetail(DayDetail dayDetail) {
       return dayDetailDao.save(dayDetail);
    }

    public DayDetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DayDetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }
    
}
