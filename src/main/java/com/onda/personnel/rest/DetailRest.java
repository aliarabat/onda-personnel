/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Timing;
import com.onda.personnel.rest.converter.DetailConverter;
import com.onda.personnel.rest.converter.TimingConverter;
import com.onda.personnel.rest.vo.DetailVo;
import com.onda.personnel.rest.vo.TimingVo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onda.personnel.service.DetailService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/Detail")
public class DetailRest {

    @Autowired
    private DetailService detailService;
    @Autowired
    private DetailConverter detailConverter;
    @Autowired
    private TimingConverter timingConverter;

    @GetMapping("/wording/{wording}")
    public DetailVo findByWording(@PathVariable String wording) {
        return new DetailConverter().toVo(detailService.findByWording(wording));
    }

    @PostMapping("/")
    public int createDetail(@RequestBody List<DetailVo> detailsVo) {
        return detailService.createDetail(detailConverter.toItem(detailsVo));
    }

    @PutMapping("/")
    public int updateDetail(@RequestBody DetailVo dayDetailVo) {
        return detailService.updateDetail(detailConverter.toItem(dayDetailVo));
    }

    @DeleteMapping("/wording/{wording}")
    public int deleteDetail(@PathVariable String wording) {
        return detailService.deleteDetail(wording);
    }

    @GetMapping("/")
    public List<DetailVo> findAll() {
        return detailConverter.toVo(detailService.findAll());
    }

    @GetMapping("/mode/{mode}")
    public List<DetailVo> findByMode(@PathVariable String mode) {
        return detailConverter.toVo(detailService.findByMode(mode));
    }

    @GetMapping("/between/startingHour/{startingHour}/startingMinute/{startingMinute}/endingHour/{endingHour}/endingMinute/{endingMinute}/isNight/{isNight}")
    public TimingVo getHoursBetween(@PathVariable int startingHour, @PathVariable int startingMinute, @PathVariable int endingHour, @PathVariable int endingMinute, @PathVariable boolean isNight) {
        return timingConverter.toVo(detailService.getHoursBetween(startingHour, startingMinute, endingHour, endingMinute, isNight));
    }


    public DetailService getDayDetailService() {
        return detailService;
    }

    public void setDayDetailService(DetailService dayDetailService) {
        this.detailService = dayDetailService;
    }

    public DetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

    public DetailConverter getDetailConverter() {
        return detailConverter;
    }

    public void setDetailConverter(DetailConverter detailConverter) {
        this.detailConverter = detailConverter;
    }

    public TimingConverter getTimingConverter() {
        return timingConverter;
    }

    public void setTimingConverter(TimingConverter timingConverter) {
        this.timingConverter = timingConverter;
    }

}
