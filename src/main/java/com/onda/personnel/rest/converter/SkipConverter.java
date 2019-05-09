/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.model.Skip;
import com.onda.personnel.rest.vo.SkipVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class SkipConverter  extends AbstractConverter<Skip, SkipVo>{
    
    
    @Autowired
    EmployeeConverter employeeConverter;    
    @Autowired
    DayDetailConverter dayDetailConverter;    

    @Override
    public Skip toItem(SkipVo vo) {
        if (vo == null) {
            return null;
        } else {
            Skip skip = new Skip();
            skip.setId(vo.getId());
            skip.setEmployee(new EmployeeConverter().toItem(vo.getEmployeeVo()));
            skip.setReference(vo.getReference());
            skip.setType(vo.getType());
            skip.setSkipDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getSkipDate())));
            return skip;
        }
        
    }
    
    @Override
    public SkipVo toVo(Skip item) {
        if (item == null) {
            return null;
        } else {
            SkipVo skipVo = new SkipVo();
            skipVo.setId(item.getId());
            skipVo.setEmployeeVo(new EmployeeConverter().toVo(item.getEmployee()));
            skipVo.setType(item.getType());
            skipVo.setReference(item.getReference());
            skipVo.setSkipDate(DateUtil.toString(DateUtil.fromDate(item.getSkipDate())));
            return skipVo;
        }
    }
    
    public EmployeeConverter getEmployeeConverter() {
        return employeeConverter;
    }
    
    public void setEmployeeConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }
    
    public DayDetailConverter getDayDetailConverter() {
        return dayDetailConverter;
    }
    
    public void setDayDetailConverter(DayDetailConverter dayDetailConverter) {
        this.dayDetailConverter = dayDetailConverter;
    }
}
