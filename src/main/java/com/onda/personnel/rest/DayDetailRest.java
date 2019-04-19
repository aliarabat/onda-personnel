/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.rest.converter.DayDetailConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.service.DayDetailService;
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

/**
 *
 * @author AMINE
 */
@RestController
@RequestMapping("/personnel-api/personnels")
public class DayDetailRest {

    @Autowired
    private DayDetailService dayDetailService;

    @GetMapping("/wording/{wording}")
    public DayDetail findByWording(@PathVariable String wording) {
        return dayDetailService.findByWording(wording);
    }

    @PostMapping("/")
    public int createDayDetail(@RequestBody DayDetailVo dayDetailVo) {
        DayDetailConverter dayDetailConverter = new DayDetailConverter();
        return dayDetailService.createDayDetail(dayDetailConverter.toItem(dayDetailVo));
    }

    @PutMapping("/wording/{wording}")
    public int updateDayDetail(@PathVariable String wording, @RequestBody DayDetailVo dayDetailVo) {
        DayDetailConverter dayDetailConverter = new DayDetailConverter();
        return dayDetailService.updateDayDetail(wording, dayDetailConverter.toItem(dayDetailVo));
    }

    @DeleteMapping("/wording/{wording}")
    public int deleteDayDeail(@PathVariable String wording) {
        return dayDetailService.deleteDayDeail(wording);
    }
    @GetMapping("/")
    public List<DayDetailVo> findAll() {
        DayDetailConverter dayDetailConverter = new DayDetailConverter();
        return dayDetailConverter.toVo(dayDetailService.findAll());
    }
    @GetMapping("/mode/{mode}")
    public List<DayDetailVo> findByMode(@PathVariable String mode) {
        DayDetailConverter dayDetailConverter = new DayDetailConverter();
        return dayDetailConverter.toVo(dayDetailService.findByMode(mode));
    }

    public DayDetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DayDetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }

}
