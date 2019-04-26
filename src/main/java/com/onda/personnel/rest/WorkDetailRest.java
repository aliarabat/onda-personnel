/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.vo.WorkDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onda.personnel.service.WorkDetailService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
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

