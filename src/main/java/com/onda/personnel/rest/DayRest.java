/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.rest.converter.DayConverter;
import com.onda.personnel.rest.converter.VacationConverter;
import com.onda.personnel.rest.vo.DayVo;
import com.onda.personnel.rest.vo.VacationVo;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.VacationService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/day")
public class DayRest {

    @Autowired
    private DayService dayService;

    @Autowired
    private DayConverter dayConverter;

    @Autowired
    private VacationConverter vacationConverter;

    @Autowired
    private VacationService vacationService;

    @PostMapping("/matricule/{matricule}")
    public int createDay(@PathVariable Integer matricule, @RequestBody List<DayVo> days) {
        days.forEach(day -> System.out.println(day.toString()));
        return dayService.createDay(matricule, dayConverter.toItem(days));
    }

    @PostMapping("/vacation/matricule/{matricule}")
    public int createVacation(@RequestBody VacationVo vacationVo, @PathVariable Integer matricule) {
        return dayService.createVacation(vacationConverter.toItem(vacationVo), matricule);
    }

    @PutMapping("/vacation/matricule/{matricule}")
    public int updateVacation(@RequestBody VacationVo vacationVo, @PathVariable Integer matricule) {
        return dayService.updateVacation(vacationConverter.toItem(vacationVo), matricule);
    }

    @GetMapping("/matricule/{matricule}/year/{year}/month/{month}")
    public List<DayVo> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(@PathVariable Integer matricule, @PathVariable int year, @PathVariable int month) {
        return new DayConverter().toVo(dayService.findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(matricule, year, month));
    }

    @GetMapping("/matricule/{matricule}/dayDate/{dayDate}")
    public DayVo findByEmployeeMatriculeAndDateOfTheDay(@PathVariable Integer matricule, @PathVariable String dayDate) {
        Date thedate = DateUtil.toDate(DateUtil.fromStringToLocalDate(dayDate));
        return new DayConverter().toVo(dayService.findByEmployeeMatriculeAndDateOfTheDay(matricule, thedate));
    }

    @GetMapping("/dateOfTheDay/{dateOfTheDay}")
    public List<DayVo> findByDateOfTheWork(@PathVariable String dateOfTheDay) {
        return new DayConverter().toVo(dayService.findByDateOfTheWork(DateUtil.toDate(DateUtil.fromStringToLocalDate(dateOfTheDay))));
    }

    @GetMapping("/dayDate/{dayDate}")
    public List<DayVo> findByDayDate(@PathVariable String dayDate) {
        Date thedate = DateUtil.toDate(DateUtil.fromStringToLocalDate(dayDate));
        return new DayConverter().toVo(dayService.findByDayDate(thedate));
    }
    @GetMapping("/vacation/id/{id}")
    public VacationVo getVacationByID(@PathVariable Long id) {
        return vacationConverter.toVo(vacationService.getVacationByID(id));
    }

    
    public DayService getDayService() {
        return dayService;
    }

    public void setDayService(DayService dayService) {
        this.dayService = dayService;
    }

    public DayConverter getDayConverter() {
        return dayConverter;
    }

    public VacationService getVacationService() {
        return vacationService;
    }

    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    public void setDayConverter(DayConverter dayConverter) {
        this.dayConverter = dayConverter;
    }

    public VacationConverter getVacationConverter() {
        return vacationConverter;
    }

    public void setVacationConverter(VacationConverter vacationConverter) {
        this.vacationConverter = vacationConverter;
    }

}
