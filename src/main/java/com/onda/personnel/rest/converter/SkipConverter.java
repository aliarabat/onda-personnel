/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onda.personnel.model.Skip;
import com.onda.personnel.rest.vo.SkipVo;
import com.onda.personnel.util.DateUtil;

/**
 *
 * @author AMINE
 */
@Component
public class SkipConverter extends AbstractConverter<Skip, SkipVo> {

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
            skip.setDetail(new DetailConverter().toItem(vo.getDetailVo()));
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
            skipVo.setDetailVo(new DetailConverter().toVo(item.getDetail()));
            return skipVo;
        }
    }
}
