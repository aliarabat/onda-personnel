/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Detail;
import com.onda.personnel.rest.converter.DetailConverter;
import com.onda.personnel.rest.vo.DetailVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.onda.personnel.service.DetailService;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/personnel-api/personnels/Detail")
public class DetailRest {

    @Autowired
    private DetailService dayDetailService;

    @GetMapping("/wording/{wording}")
    public Detail findByWording(@PathVariable String wording) {
        return dayDetailService.findByWording(wording);
    }

    @PostMapping("/")
    public int createDetail(@RequestBody DetailVo dayDetailVo) {
        DetailConverter dayDetailConverter = new DetailConverter();
        return dayDetailService.createDetail(dayDetailConverter.toItem(dayDetailVo));
    }

    @PutMapping("/wording/{wording}")
    public int updateDayDetail(@PathVariable String wording, @RequestBody DetailVo dayDetailVo) {
        DetailConverter dayDetailConverter = new DetailConverter();
        return dayDetailService.updateDayDetail(wording, dayDetailConverter.toItem(dayDetailVo));
    }

    @DeleteMapping("/wording/{wording}")
    public int deleteDayDeail(@PathVariable String wording) {
        return dayDetailService.deleteDayDeail(wording);
    }
    @GetMapping("/")
    public List<DetailVo> findAll() {
        DetailConverter dayDetailConverter = new DetailConverter();
        return dayDetailConverter.toVo(dayDetailService.findAll());
    }
    @GetMapping("/mode/{mode}")
    public List<DetailVo> findByMode(@PathVariable String mode) {
        DetailConverter dayDetailConverter = new DetailConverter();
        return dayDetailConverter.toVo(dayDetailService.findByMode(mode));
    }

    public DetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }

}
