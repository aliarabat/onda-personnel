/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Day;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.converter.DayConverter;
import com.onda.personnel.rest.vo.DayVo;
import com.onda.personnel.service.DayService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/personnel-api/personnels/day")
public class DayRest {

    @Autowired
    private DayService dayService;

    @Autowired
    @Qualifier("dayConverter")
    private AbstractConverter<Day, DayVo> dayConverter;

    @PostMapping("/matricule/{matricule}")
    public int createDay(@PathVariable Integer matricule,@RequestBody List<DayVo> days) {
        return dayService.createDay(matricule, dayConverter.toItem(days));
    }

    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

}
