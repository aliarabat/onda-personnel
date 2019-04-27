/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Mission;
import com.onda.personnel.bean.Vacation;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.rest.vo.MissionVo;
import com.onda.personnel.rest.vo.VacationVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class VacationConverter extends AbstractConverter<Vacation, VacationVo> {

    @Override
    public Vacation toItem(VacationVo vo) {
        if (vo == null) {
            return null;
        } else {
            Vacation vac = new Vacation();
            vac.setId(vo.getId());
            vac.setReference(vo.getReference());
            vac.setEndingDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getEndingDate())));
            vac.setStartingDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getStartingDate())));
            vac.setType(vo.getType());
            vac.setEmployee(new EmployeeConverter().toItem(vo.getEmployee()));

            return vac;
        }

    }

    @Override
    public VacationVo toVo(Vacation item) {
        if (item == null) {
            return null;
        } else {
            VacationVo vacVo = new VacationVo();
            vacVo.setId(item.getId());
            vacVo.setReference(item.getReference());
            vacVo.setEndingDate(DateUtil.toString(DateUtil.fromDate(item.getEndingDate())));
            vacVo.setStartingDate(DateUtil.toString(DateUtil.fromDate(item.getEndingDate())));
            vacVo.setType(item.getType());
            vacVo.setEmployee(new EmployeeConverter().toVo(item.getEmployee()));
            
            return vacVo;
        }
    }
}


