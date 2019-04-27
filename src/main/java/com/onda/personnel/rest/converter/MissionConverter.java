/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Mission;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.rest.vo.MissionVo;
import org.springframework.stereotype.Component;

/**
 * @author AMINE
 */
@Component
public class MissionConverter extends AbstractConverter<Mission, MissionVo> {

    @Override
    public Mission toItem(MissionVo vo) {
        if (vo == null) {
            return null;
        } else {
            Mission miss = new Mission();
            miss.setId(vo.getId());
            miss.setReference(vo.getReference());
            miss.setStartingDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getStartingDate())));
            miss.setType(vo.getType());
            miss.setEmployee(new EmployeeConverter().toItem(vo.getEmployee()));
            return miss;
        }
    }

    @Override
    public MissionVo toVo(Mission item) {
        if (item == null) {
            return null;
        } else {
            MissionVo missVo = new MissionVo();
            missVo.setId(item.getId());
            missVo.setReference(item.getReference());
            missVo.setStartingDate(DateUtil.toString(DateUtil.fromDate(item.getStartingDate())));
            missVo.setType(item.getType());
            missVo.setEmployee(new EmployeeConverter().toVo(item.getEmployee()));
            return missVo;
        }
    }

}
