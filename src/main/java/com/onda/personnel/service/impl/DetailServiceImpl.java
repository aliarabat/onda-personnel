/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.personnel.dao.DetailDao;
import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Timing;
import com.onda.personnel.service.DetailService;
import com.onda.personnel.util.PeriodUtil;

/**
 * @author AMINE
 */
@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailDao detailDao;
    @Autowired
    private DetailService detailService;

    @Override
    public Detail findByWording(String wording) {
        return detailDao.findByWording(wording);
    }

    @Override
    public int createDetail(List<Detail> details) {
        for (Detail detail : details) {
            Detail dd = findByWording(detail.getWording());
            if (dd != null) {
                return -1;
            } else if (dd == null) {

                if (detail.getWording().equals("R")) {
                    detail.setMode("Normal");
                    detail.setEndingTime(detail.getEndingTime());
                    detail.setStartingTime(detail.getStartingTime());
                    detail.setHe(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), false));
                    detail.setHn(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), true));
                    detail.setPan(detail.getPan());
                    detailDao.save(detail);
                    Detail ddd = new Detail();
                    ddd.setMode("Ramadan");
                    ddd.setWording(detail.getWording());
                    ddd.setEndingTime(detail.getEndingTime());
                    ddd.setStartingTime(detail.getStartingTime());
                    ddd.setHe(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), false));
                    ddd.setHn(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), true));
                    ddd.setPan(detail.getPan());
                    detailDao.save(ddd);

                } else {
                    detail.setHe(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), false));
                    detail.setHn(getHoursBetween(detail.getStartingTime().getHour(), detail.getStartingTime().getMinute(), detail.getEndingTime().getHour(), detail.getEndingTime().getMinute(), true));
                    detailDao.save(detail);
                }

            }
        }
        return 1;
    }

    @Override
    public int updateDetail(Detail detail) {
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

    @Override
    public Detail getDetailById(Long id) {
        return detailDao.getOne(id);
    }

    @Override
    public Detail findTopByWording(String wording) {
        return detailDao.findTopByWording(wording);
    }

    @Override
    public long count() {
        if (detailDao.findTopByWording("R")!=null) {
            long res=detailDao.count()-2;
            return res;
        }
        return detailDao.count();
    }

    public DetailDao getDayDetailDao() {
        return detailDao;
    }

    public void setDayDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    public DetailDao getDetailDao() {
        return detailDao;
    }

    public void setDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    public DetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

}
