/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Detail;
import com.onda.personnel.bean.Timing;
import com.onda.personnel.common.util.PeriodUtil;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.personnel.dao.DetailDao;
import com.onda.personnel.service.DetailService;
import java.util.Date;

/**
 *
 * @author AMINE
 */
@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailDao detailDao;

    @Override
    public Detail findByWording(String wording) {
        return detailDao.findByWording(wording);
    }

    @Override
    public int createDetail(List<Detail> details) {
        for (Detail detail : details) {
            Detail dd = findByWording(detail.getWording());
            if (dd == null) {
                dd = new Detail();
                dd.setStartingTime(detail.getStartingTime());
                dd.setEndingTime(detail.getEndingTime());
                dd.setHe(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), false));
                dd.setHn(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), true));
                dd.setPan(detail.getPan());
                dd.setMode(detail.getMode());
                dd.setWording(detail.getWording());
                detailDao.save(dd);
            }
        }
        return 1;
    }

    @Override
    public int updateDetail( Detail detail) {
        Detail oldDayDetail = detailDao.getOne(detail.getId());
        if (oldDayDetail == null) {
            return -1;
        } else {
            oldDayDetail.setEndingTime(detail.getEndingTime());
            oldDayDetail.setStartingTime(detail.getStartingTime());
            oldDayDetail.setHe(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), false));
            oldDayDetail.setHn(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), true));
            oldDayDetail.setMode(detail.getMode());
            oldDayDetail.setPan(detail.getPan());
            oldDayDetail.setWording(detail.getWording());
            detailDao.save(oldDayDetail);
            return 1;
        }
    }

    @Override
    public int deleteDetail(String wording) {
        Detail detail = findByWording(wording);
        if (detail == null) {
            return -1;
        } else {
            detailDao.delete(detail);
            return 1;
        }
    }

    @Override
    public Timing getHoursBetween(int startingHour, int startingMinute, int endingHour, int endingMinute, boolean isNight) {
           return PeriodUtil.getHoursBetween(startingHour, startingMinute, endingHour, endingMinute, isNight);
    }

    @Override
    public List<Detail> findAll() {
        return detailDao.findAll();
    }

    @Override
    public List<Detail> findByMode(String mode) {
        return detailDao.findByMode(mode);
    }

    public DetailDao getDayDetailDao() {
        return detailDao;
    }

    public void setDayDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    

}
