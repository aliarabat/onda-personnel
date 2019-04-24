/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Work;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.service.WorkService;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/personnel-api/personnels/work")
public class WorkRest {

    @Autowired
    private WorkService workService;

    @GetMapping("/matricule/{matricule}/workDetailDate/{workDetailDate}")
    public Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(@PathVariable Integer matricule, @PathVariable String workDetailDate) {
        return workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule, new Date(workDetailDate));
    }

    @GetMapping("/matricule/{matricule}")
    public Work findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(@PathVariable Integer matricule) {
        return workService.findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(matricule);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        workService.deleteById(id);
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

}
