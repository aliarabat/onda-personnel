/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.model.Vacation;
import com.onda.personnel.rest.converter.VacationConverter;
import com.onda.personnel.rest.vo.VacationVo;
import com.onda.personnel.service.VacationService;
import groovy.transform.AutoClone;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/vacation")
public class vacationRest {

    @Autowired
    private VacationConverter vacationConverter;

    @Autowired
    private VacationService vacationService;

    @DeleteMapping("/id/{id}")
    public int removeVacation(@PathVariable Long id) {
        return vacationService.removeVacation(id);
    }

    @GetMapping("/")
    public List<VacationVo> findAllVacation() {
        return vacationConverter.toVo(vacationService.findAllVacation());
    }

    public void setVacationConverter(VacationConverter vacationConverter) {
        this.vacationConverter = vacationConverter;
    }

    public void setVacationService(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    public VacationConverter getVacationConverter() {
        return vacationConverter;
    }

    public VacationService getVacationService() {
        return vacationService;
    }

}
