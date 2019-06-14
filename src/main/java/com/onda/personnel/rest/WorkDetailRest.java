/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;


import com.onda.personnel.model.WorkDetail;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.converter.WorkDetailConverter;
import com.onda.personnel.rest.vo.WorkDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.onda.personnel.service.WorkDetailService;

/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/personnel-api/personnels/workDetail")
public class WorkDetailRest {
    @Autowired
    private WorkDetailService workDetailSevice;

    @Autowired
    @Qualifier("workDetailConverter")
    private AbstractConverter<WorkDetail, WorkDetailVo> workDetailConverter;

    /*
        @GetMapping("/workDetailDate/{workDetailDate}")
       public List<WorkDetail> findByWorkDetailDate(@PathVariable String workDetailDate) {
            return workDetailSevice.findByWorkDetailDate(DateUtil.fromStringToLocalDate(workDetailDate).plusDays(1));
        }
    */

    @PutMapping("/")
    public WorkDetailVo updateWorkDetail(@RequestBody WorkDetailVo workDetailVo) {
        return workDetailConverter.toVo(workDetailSevice.updateWorkDetail(workDetailConverter.toItem(workDetailVo)));
    }
        @GetMapping("/matricule/{matricule}/year/{year}/month/{month}")
        public WorkDetailVo findByEmployeeMatriculeAndWorkDetailDate(@PathVariable Integer matricule,@PathVariable int year,@PathVariable int month) {
        return new WorkDetailConverter().toVo(workDetailSevice.findByEmployeeMatriculeAndWorkDetailDate(matricule, year, month)) ;
    }


    public WorkDetailService getWorkDetailSevice() {
        return workDetailSevice;
    }

    
    public void setWorkDetailSevice(WorkDetailService workDetailSevice) {
        this.workDetailSevice = workDetailSevice;
    }

    public AbstractConverter<WorkDetail, WorkDetailVo> getWorkDetailConverter() {
        return workDetailConverter;
    }

    public void setWorkDetailConverter(AbstractConverter<WorkDetail, WorkDetailVo> workDetailConverter) {
        this.workDetailConverter = workDetailConverter;
    }


}

