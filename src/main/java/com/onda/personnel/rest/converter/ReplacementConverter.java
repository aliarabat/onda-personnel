/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.util.DateUtil;
import com.onda.personnel.model.Replacement;
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
            rep.setOriginalEmployee(new EmployeeConverter().toItem(vo.getOriginalEmployee()) );
            rep.setReplacedEmpolyee(new EmployeeConverter().toItem(vo.getReplacedEmployee()));
            rep.setReplacementDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getReplacementDate())));
            rep.setReference(vo.getReference());
            rep.setDetail(new DetailConverter().toItem(vo.getDetailVo()));

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
            repVo.setOriginalEmployee(new EmployeeConverter().toVo(item.getOriginalEmployee()));
            repVo.setReplacedEmployee(new EmployeeConverter().toVo(item.getReplacedEmpolyee()));
            repVo.setReplacementDate(DateUtil.toString(DateUtil.fromDate(item.getReplacementDate())));
            repVo.setReference(item.getReference());
            repVo.setDetailVo(new DetailConverter().toVo(item.getDetail()));
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
