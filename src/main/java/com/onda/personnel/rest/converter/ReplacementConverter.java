/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Replacement;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.rest.vo.ReplacementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class ReplacementConverter extends AbstractConverter<Replacement, ReplacementVo>  {

    @Autowired
    EmployeeConverter employeeConverter;

    @Override
    public Replacement toItem(ReplacementVo vo) {
        if (vo == null) {
            return null;
        } else {
            Replacement rep = new Replacement();
            rep.setId(vo.getId());
            rep.setOriginalEmployee(employeeConverter.toItem(vo.getOriginalEmployee()));
            rep.setReplacedEmpolyee(employeeConverter.toItem(vo.getReplacedEmpolyee()));
            rep.setReplacementDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getReplacementDate())));
            rep.setReference(vo.getReference());

            return rep;
        }
    }

    @Override
    public ReplacementVo toVo(Replacement item) {
        if (item == null) {
            return null;
        } else {
            ReplacementVo repVo = new ReplacementVo();
            repVo.setId(item.getId());
            repVo.setOriginalEmployee(employeeConverter.toVo(item.getOriginalEmployee()));
            repVo.setReplacedEmpolyee(employeeConverter.toVo(item.getReplacedEmpolyee()));
            repVo.setReplacementDate(DateUtil.toString(DateUtil.fromDate(item.getReplacementDate())));
            repVo.setReference(item.getReference());
            return repVo;
        }
    }

    public EmployeeConverter getEmployeeConverter() {
        return employeeConverter;
    }

    public void setEmployeeConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

}
