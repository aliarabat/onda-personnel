/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.Vacation;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.service.DayService;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/day")
public class DayRest {

    @Autowired
    private DayService dayService;

    @PostMapping("/matricule/{matricule}")
    public int createDay(@PathVariable Integer matricule, @RequestBody List<Day> days) {
        return dayService.createDay(matricule, days);
    }
    
    @PostMapping("/vacation/")
    public int createVacation(@RequestBody Vacation vacation) {
        return dayService.createVacation(vacation);
    }

    @GetMapping("/matricule/{matricule}/year/{year}/month/{month}")
    public List<Day> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(@PathVariable Integer matricule, @PathVariable int year, @PathVariable int month) {
        return dayService.findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(matricule, year, month);
    }

    @GetMapping("/matricule/{matricule}/dayDate/{dayDate}")
    public Day findByEmployeeMatriculeAndDateOfTheDay(@PathVariable Integer matricule, @PathVariable String dayDate) {
        return dayService.findByEmployeeMatriculeAndDateOfTheDay(matricule, DateUtil.toDate(DateUtil.fromStringToLocalDate(dayDate)));
    }

    @GetMapping("/dayDate/{dayDate}")
    public Day findByDayDate(@PathVariable String dayDate) {
        return dayService.findByDayDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(dayDate)));
    }

    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

}
