/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.common.util.StringUtil;
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
            workDetail.setHjf(new TimingConverter().toItem(vo.getHjf()));
            workDetail.setHn(new TimingConverter().toItem(vo.getHn()));
            workDetail.setPan(NumberUtil.toInteger(vo.getPan()));
            workDetail.setDays(new DayConverter().toItem(vo.getDaysVo()));
            workDetail.setWorkDetailDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getWorkDetailDate())));
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
            workDetailVo.setHjf(new TimingConverter().toVo(item.getHjf()));
            workDetailVo.setHn(new TimingConverter().toVo(item.getHn()));
            workDetailVo.setPan(NumberUtil.toString(item.getPan()));
            workDetailVo.setDaysVo(new DayConverter().toVo(item.getDays()));
            workDetailVo.setWorkDetailDate(DateUtil.toString(DateUtil.fromDate(item.getWorkDetailDate())));
            return workDetailVo;
        }
    }

}
