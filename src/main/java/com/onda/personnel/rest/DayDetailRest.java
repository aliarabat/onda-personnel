/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.Mission;
import com.onda.personnel.model.Replacement;
import com.onda.personnel.model.Skip;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.converter.DayDetailConverter;
import com.onda.personnel.rest.converter.SkipConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.rest.vo.EmployeeVo;
import com.onda.personnel.rest.vo.SkipVo;
import com.onda.personnel.service.DayDetailService;
import com.onda.personnel.service.SkipService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaafarDiyaou
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/dayDetail")
public class DayDetailRest {

    @Autowired
    DayDetailService dayDetailService;

    @Autowired
    SkipService skipService;

    @Autowired
    public DayDetailConverter dayDetailConverter;

    @Autowired
    @Qualifier("skipConverter")
    private AbstractConverter<Skip, SkipVo> skipConverter;

    @PutMapping("/mission/matricule/{matricule}/wordingDetail/{wordingDetail}")
    public int updateDayDetailMission(@PathVariable Integer matricule, @PathVariable String wordingDetail, @RequestBody Mission mission) {
        return dayDetailService.updateDayDetailMission(matricule, wordingDetail, mission);
    }

    @PutMapping("/skip/matricule/{matricule}/wordingDetail/{wordingDetail}")
    public int updateDayDetailSkip(@PathVariable Integer matricule, @PathVariable String wordingDetail, @RequestBody SkipVo skipVo) {
        return dayDetailService.updateDayDetailSkip(matricule, wordingDetail, skipConverter.toItem(skipVo));
    }

    @PutMapping("/replacement/matricule/{matricule}/matricule1/{matricule1}/wordingDetail/{wordingDetail}")
    public int updateDayDetailReplacement(@PathVariable Integer matricule, @PathVariable Integer matricule1, @PathVariable String wordingDetail, @RequestBody Replacement replacement) {
        return dayDetailService.updateDayDetailReplacement(matricule, matricule1, wordingDetail, replacement);
    }

    @GetMapping("/skip/")
    public List<SkipVo> findAllSkips() {
        return skipConverter.toVo(skipService.findAllSkips());
    }

    @GetMapping("/")
    public List<DayDetailVo> findBySkipIsNotNull() {
        return dayDetailConverter.toVo(dayDetailService.findBySkipIsNotNull());
    }

    @DeleteMapping("/id/{id}")
    public int removeSkip(@PathVariable Long id) {
        return skipService.removeSkip(id);
    }

    @PutMapping("/matricule/{matricule}/dateMission/{dateMission}/wordingDetail/{wordingDetail}")
    public DayDetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DayDetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }

    public SkipService getSkipService() {
        return skipService;
    }

    public void setSkipService(SkipService skipService) {
        this.skipService = skipService;
    }

    public AbstractConverter<Skip, SkipVo> getSkipConverter() {
        return skipConverter;
    }

    public void setSkipConverter(AbstractConverter<Skip, SkipVo> skipConverter) {
        this.skipConverter = skipConverter;
    }

    public DayDetailConverter getDayDetailConverter() {
        return dayDetailConverter;
    }

    public void setDayDetailConverter(DayDetailConverter dayDetailConverter) {
        this.dayDetailConverter = dayDetailConverter;
    }

}
