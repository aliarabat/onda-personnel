/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Detail;
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
    private DetailDao dayDetailDao;

    @Override
    public Detail findByWording(String wording) {
        return dayDetailDao.findByWording(wording);
    }

    @Override
    public int createDetail(Detail dayDetail) {
        Detail dd = findByWording(dayDetail.getWording());
        if (dd != null) {
            return -1;
        } else {
            dd = new Detail();

            LocalTime st = dayDetail.getStartingTime();
            LocalTime et = dayDetail.getEndingTime();
            dd.setStartingTime(st);
            dd.setEndingTime(et);
            dd.setHe(PeriodUtil.getHoursBetween(dayDetail.getStartingTime().getHour(), dayDetail.getEndingTime().getHour(), false));
            dd.setHn(PeriodUtil.getHoursBetween(dayDetail.getStartingTime().getHour(), dayDetail.getEndingTime().getHour(), true));
            dd.setPan(dayDetail.getPan());
            dd.setMode(dayDetail.getMode());
            dd.setWording(dayDetail.getWording());
            dayDetailDao.save(dd);
            return 1;
        }
    }

    @Override
    public int updateDayDetail(String wording, Detail dayDetail) {
        Detail oldDayDetail = findByWording(wording);
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
        Detail dayDetail = findByWording(wording);
        if (dayDetail == null) {
            return -1;
        } else {
            dayDetailDao.delete(dayDetail);
            return 1;
        }
    }

    @Override
    public List<Detail> findAll() {
        return dayDetailDao.findAll();
    }

    @Override
    public List<Detail> findByMode(String mode) {
        return dayDetailDao.findByMode(mode);
    }

    public DetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }

}
