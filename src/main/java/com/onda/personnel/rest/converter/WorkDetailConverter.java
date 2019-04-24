/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.rest.vo.WorkDetailVo;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class WorkDetailConverter extends AbstractConverter<WorkDetail, WorkDetailVo> {

    @Override
    public WorkDetail toItem(WorkDetailVo vo) {
        if (vo == null) {
            return null;
        } else {
            WorkDetail workDetail = new WorkDetail();
            workDetail.setId(vo.getId());
            workDetail.setHjf(NumberUtil.toInteger(vo.getHjf()));
            workDetail.setHn(NumberUtil.toInteger(vo.getHn()));
            workDetail.setPan(NumberUtil.toInteger(vo.getPan()));
            workDetail.setDays(new DayConverter().toItem(vo.getDays()));
            workDetail.setTestDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getTestDate())));
            return workDetail;
        }
    }

    @Override
    public WorkDetailVo toVo(WorkDetail item) {
        if (item == null) {
            return null;
        } else {
            WorkDetailVo workDetailVo = new WorkDetailVo();
            workDetailVo.setId(item.getId());
            workDetailVo.setHjf(NumberUtil.toString(item.getHjf()));
            workDetailVo.setHn(NumberUtil.toString(item.getHn()));
            workDetailVo.setPan(NumberUtil.toString(item.getPan()));
            workDetailVo.setDays(new DayConverter().toVo(item.getDays()));
            workDetailVo.setTestDate(DateUtil.toString(DateUtil.fromDate(item.getTestDate())));
            return workDetailVo;
        }
    }

}
