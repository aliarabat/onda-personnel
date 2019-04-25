/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.vo.WorkDetailVo;
import com.onda.personnel.service.WorkDetailSevice;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RestController
@RequestMapping("/personnel-api/personnels/workDetail")
public class WorkDetailRest {
    @Autowired
    private WorkDetailSevice workDetailSevice;

    @Autowired
    @Qualifier("workDetailConverter")
    private AbstractConverter<WorkDetail, WorkDetailVo> workDetailConverter;
/*
    @GetMapping("/workDetailDate/{workDetailDate}")
   public List<WorkDetail> findByWorkDetailDate(@PathVariable String workDetailDate) {
        return workDetailSevice.findByWorkDetailDate(DateUtil.fromStringToLocalDate(workDetailDate).plusDays(1));
    }
*/
    public WorkDetailSevice getWorkDetailSevice() {
        return workDetailSevice;
    }

    public void setWorkDetailSevice(WorkDetailSevice workDetailSevice) {
        this.workDetailSevice = workDetailSevice;
    }

    public AbstractConverter<WorkDetail, WorkDetailVo> getWorkDetailConverter() {
        return workDetailConverter;
    }

    public void setWorkDetailConverter(AbstractConverter<WorkDetail, WorkDetailVo> workDetailConverter) {
        this.workDetailConverter = workDetailConverter;
    }
    
    
   
}

