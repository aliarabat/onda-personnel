/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.util.DateUtil;
import com.onda.personnel.model.Work;
import com.onda.personnel.rest.converter.WorkConverter;
import com.onda.personnel.rest.vo.WorkVo;
import com.onda.personnel.service.WorkService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/work")
public class WorkRest {

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkConverter workConverter;

    @GetMapping("/matricule/{matricule}/workDetailDate/{workDetailDate}")
    public WorkVo findByEmployeeMatriculeAndWorkDetailWorkDetailDate(@PathVariable Integer matricule,
            @PathVariable String workDetailDate) {
        return new WorkConverter().toVo(workService.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule,
                DateUtil.toDate(DateUtil.fromStringToLocalDate(workDetailDate))));
    }

    @GetMapping("/matricule/{matricule}")
    public Work findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(@PathVariable Integer matricule) {
        return workService.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
    }

    @GetMapping("matricule/{matricule}/annee/{annee}/")
    public List<WorkVo> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(@PathVariable Integer matricule,
            @PathVariable Integer annee) {
        return new WorkConverter()
                .toVo(workService.findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(matricule, annee));
    }

    @GetMapping("/annee/{annee}")
    public List<WorkVo> findAllByWorkDetailWorkDetailDateBetween(@PathVariable Integer annee) {
        return new WorkConverter().toVo(workService.findAllByWorkDetailWorkDetailDateBetween(annee));
    }

    @GetMapping("/workDate/{workDate}")
    public List<WorkVo> findWorksByDate(@PathVariable String workDate) {
        return new WorkConverter()
                .toVo(workService.findWorksByDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(workDate))));
    }

    @GetMapping("/matricule/{matricule}/year/{year}/month/{month}")
    public List<WorkVo> findByEmployeeMatriculeAndMonthAndYear(@PathVariable Integer matricule, @PathVariable int year,
            @PathVariable int month) {
        return new WorkConverter().toVo(workService.findByEmployeeMatriculeAndMonthAndYear(matricule, year, month));
    }

    @GetMapping("/year/{year}/month/{month}")
    public List<WorkVo> findByMonthAndYear(@PathVariable int year, @PathVariable int month) {
        return new WorkConverter().toVo(workService.findByMonthAndYear(year, month));
    }

    @GetMapping("/workDetailDate/{workDetailDate}")
    public List<WorkVo> findByWorkDetailWorkDetailDate(@PathVariable String workDetailDate) {
        return new WorkConverter().toVo(workService
                .findByWorkDetailWorkDetailDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(workDetailDate))));
    }

    @GetMapping("/ckeckdates/matricule/{matricule}")
    public List<String> findFromDateToDate(@PathVariable Integer matricule) {
        return workService.findFromDateToDate(matricule);
    }

    @RequestMapping(value = "/generatedoc/year/{year}/month/{month}/type/pdf", produces = MediaType.ALL_VALUE, method = RequestMethod.GET)
    public void generateDoc(HttpServletResponse response, @PathVariable int year, @PathVariable int month)
            throws JRException, IOException {
        workService.printDoc(response, year, month);
    }

    @RequestMapping(value = "/generatedoc/year/{year}/month/{month}/type/xlsx", produces = MediaType.ALL_VALUE, method = RequestMethod.GET)
    public void generateXlsx(HttpServletResponse response, @PathVariable int year, @PathVariable int month)
            throws JRException, IOException {
        workService.printXlsx(response, year, month);
    }

    @GetMapping("/worktoprint/year/{year}/month/{month}")
    public WorkVo findTopByWorkDetailWorkDetailDateOrderByWorkDetailWorkDetailDateDesc(@PathVariable int year,
            @PathVariable int month) {
        LocalDate ld = LocalDate.of(year, month, 1);
        return workConverter.toVo(
                workService.findTopByWorkDetailWorkDetailDateOrderByWorkDetailWorkDetailDateDesc(DateUtil.toDate(ld)));
    }

    @GetMapping("/printgraph/matricule/{matricule}/year/{year}")
    public void printGraphForEmployee(HttpServletResponse response, @PathVariable int matricule,
            @PathVariable int year) {
        workService.printGraphForEmployee(response, matricule, year);
    }

    @GetMapping("emloyetograph/matricule/{matricule}/year/{year}")
    public WorkVo findTopByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(@PathVariable Integer matricule, @PathVariable int year) {
        return new WorkConverter().toVo(workService.findTopByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(matricule, year));
    }

    @GetMapping("/countall/year/{year}")
    public List<WorkVo> countAll(@PathVariable int year) {
        return workService.countAll(year);
    }

    @GetMapping("/")
    public List<WorkVo> findAll() {
        return new WorkConverter().toVo(workService.findAll());
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    public WorkConverter getWorkConverter() {
        return workConverter;
    }

    public void setWorkConverter(WorkConverter workConverter) {
        this.workConverter = workConverter;
    }
}
