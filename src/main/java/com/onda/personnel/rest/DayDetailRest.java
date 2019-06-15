/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onda.personnel.dao.DayDetailDao;
import com.onda.personnel.model.DayDetail;
import com.onda.personnel.rest.converter.DayDetailConverter;
import com.onda.personnel.rest.converter.MissionConverter;
import com.onda.personnel.rest.converter.ReplacementConverter;
import com.onda.personnel.rest.converter.SkipConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.rest.vo.MissionVo;
import com.onda.personnel.rest.vo.ReplacementVo;
import com.onda.personnel.rest.vo.SkipVo;
import com.onda.personnel.service.DayDetailService;
import com.onda.personnel.service.SkipService;

/**
 * @author JaafarDiyaou
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk", "http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/dayDetail")
public class DayDetailRest {

    @Autowired
    DayDetailService dayDetailService;

    @Autowired
    SkipService skipService;

    @Autowired
    DayDetailDao dayDetailDao;

    @PutMapping("/mission/matricule/{matricule}")
    public int updateDayDetailMission(@PathVariable Integer matricule, @RequestBody MissionVo missionVo) {
        return dayDetailService.updateDayDetailMission(matricule, new MissionConverter().toItem(missionVo));
    }

    @PutMapping("/skip/matricule/{matricule}/wordingDetail/{wordingDetail}")
    public int updateDayDetailSkip(@PathVariable Integer matricule, @PathVariable String wordingDetail, @RequestBody SkipVo skipVo) {
        return dayDetailService.updateDayDetailSkip(matricule, wordingDetail, new SkipConverter().toItem(skipVo));
    }

    @PutMapping("/replacement/matricule/{matricule}/matricule1/{matricule1}/wordingDetail/{wordingDetail}")
    public int updateDayDetailReplacement(@PathVariable Integer matricule, @PathVariable Integer matricule1, @PathVariable String wordingDetail, @RequestBody ReplacementVo replacementVo) {
        return dayDetailService.updateDayDetailReplacement(matricule, matricule1, wordingDetail, new ReplacementConverter().toItem(replacementVo));
    }

    @GetMapping("/all")
    public List<DayDetailVo> findAll() {
        return new DayDetailConverter().toVo(dayDetailService.findAll());
    }

    @GetMapping("/")
    public List<DayDetailVo> findBySkipIsNotNull() {
        return new DayDetailConverter().toVo(dayDetailService.findBySkipIsNotNull());
    }

    @GetMapping("/Mission/")
    public List<DayDetailVo> findByMissionIsNotNull() {
        return new DayDetailConverter().toVo(dayDetailService.findByMissionIsNotNull());
    }

    @DeleteMapping("/id/{id}")
    public int removeSkip(@PathVariable Long id) {
        return skipService.removeSkip(id);
    }

    @PutMapping("/mission/id/{idDayDetail}")
    public int updateDayDetailByDeletingMission(@PathVariable Long idDayDetail) {
        DayDetail dayDetail = dayDetailDao.getOne(idDayDetail);
        return dayDetailService.updateDayDetailByDeletingMission(dayDetail);
    }

    @PutMapping("/replacement/id/{idDayDetail}")
    public int updateDayDetailByDeletingReplacement(@PathVariable Long idDayDetail) {
        DayDetail dayDetail = dayDetailDao.getOne(idDayDetail);
        return dayDetailService.updateDayDetailByDeletingReplacement(dayDetail);
    }

    @GetMapping("/id/{id}")
    public DayDetailVo findById(@PathVariable Long id) {
        return new DayDetailConverter().toVo(dayDetailService.findById(id));
    }

    @GetMapping("/replacement/")
    public List<DayDetailVo> findByReplacementIsNotNullAndDetailIsNotNull() {
        return new DayDetailConverter().toVo(dayDetailService.findByReplacementIsNotNullAndDetailIsNotNull());
    }

    @GetMapping("/Mission/skip/replacement")
    public List<DayDetail> findByDetailIsNullAndSkipIsNullAndRepalcementIsNullAndMissionIsNull() {
        return dayDetailService.findByDetailIsNullAndSkipIsNullAndRepalcementIsNullAndMissionIsNull();
    }

    @DeleteMapping("/null")
    public void deleteDayDetailWhereIsNull() {
        dayDetailService.deleteDayDetailWhereIsNull();
    }

    @PutMapping

    public DayDetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DayDetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }

    public DayDetailDao getDayDetailDao() {
        return dayDetailDao;
    }

    public void setDayDetailDao(DayDetailDao dayDetailDao) {
        this.dayDetailDao = dayDetailDao;
    }

    public SkipService getSkipService() {
        return skipService;
    }

    public void setSkipService(SkipService skipService) {
        this.skipService = skipService;
    }

}
