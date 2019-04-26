/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Work;
import com.onda.personnel.service.WorkService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/personnel-api/personnels/work")
public class WorkRest {

    @Autowired
    private WorkService workService;

    @GetMapping("/matricule/{matricule}/workDetailDate/{workDetailDate}")
    public Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(@PathVariable Integer matricule,@PathVariable String workDetailDate) {
        return workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule, new Date(workDetailDate));
    }

    @GetMapping("/matricule/{matricule}")
    public Work findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(@PathVariable Integer matricule) {
        return workService.findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(matricule);
    }
    @GetMapping("/matricule/{matricule}/year/{year}/month/{month}")
    public Work findByEmployeeMatriculeAndMonthAndYear(@PathVariable Integer matricule,@PathVariable int year,@PathVariable int month) {
        return workService.findByEmployeeMatriculeAndMonthAndYear(matricule, year, month);
    }
    @GetMapping("/year/{year}/month/{month}")
    public List<Work> findByMonthAndYear(@PathVariable int year,@PathVariable  int month) {
        return workService.findByMonthAndYear(year, month);
    }
    @GetMapping("/workDetailDate/{workDetailDate}")
    public List<Work> findByWorkDetailWorkDetailDate(@PathVariable Date workDetailDate) {
        return workService.findByWorkDetailWorkDetailDate(workDetailDate);
    }
    
    
    
    
    

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

}
