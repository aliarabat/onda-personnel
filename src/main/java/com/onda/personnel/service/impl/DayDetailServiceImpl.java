/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.service.DayDetailService;
import java.time.LocalTime;
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

    @Override
    public DayDetail findByWording(String wording) {
        return dayDetailDao.findByWording(wording);
    }

    @Override
    public DayDetail createDayDetail(DayDetail dayDetail) {
        DayDetail dd = findByWording(dayDetail.getWording());
        if (dd != null) {
            return null;
        } else {
            dd=new DayDetail();
            dd.setStartingTime(dayDetail.getStartingTime().plusHours(1));
            dd.setEndingTime(dayDetail.getEndingTime().plusHours(1));
            dd.setHe(dayDetail.getHe());
            dd.setHn(dayDetail.getHn());
            dd.setPan(dayDetail.getPan());
            dd.setMode(dayDetail.getMode());
            dd.setWording(dayDetail.getWording());
            dayDetailDao.save(dd);
            return dd;
        }
    }

    @Override
    public int updateDayDetail(String wording, DayDetail dayDetail) {
        DayDetail oldDayDetail = findByWording(wording);
        if (oldDayDetail == null) {
            return -1;
        } else {
            oldDayDetail.setEndingTime(dayDetail.getEndingTime().plusHours(1));
            oldDayDetail.setStartingTime(dayDetail.getStartingTime().plusHours(1));
            oldDayDetail.setHe(dayDetail.getHe());
            oldDayDetail.setHn(dayDetail.getHn());
            oldDayDetail.setMode(dayDetail.getMode());
            oldDayDetail.setPan(dayDetail.getPan());
            oldDayDetail.setWording(dayDetail.getWording());
            dayDetailDao.save(oldDayDetail);
            return 1;
        }
    }

    @Override
    public int deleteDayDeail(String wording) {
        DayDetail dayDetail = findByWording(wording);
        if (dayDetail == null) {
            return -1;
        } else {
            dayDetailDao.delete(dayDetail);
            return 1;
        }
    }

    @Override
    public List<DayDetail> findAll() {
        return dayDetailDao.findAll();
    }

    @Override
    public List<DayDetail> findByMode(String mode) {
        return dayDetailDao.findByMode(mode);
    }

    public DayDetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DayDetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }

}
