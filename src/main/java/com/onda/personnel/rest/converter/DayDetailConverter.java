/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.rest.vo.DayDetailVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class DayDetailConverter extends AbstractConverter<DayDetail, DayDetailVo> {

    @Override
    public DayDetail toItem(DayDetailVo vo) {
        if (vo == null) {
            return null;
        } else {
            DayDetail dayDetail = new DayDetail();
            dayDetail.setId(vo.getId());
            dayDetail.setHe(NumberUtil.toInteger(vo.getHe()));
            dayDetail.setHn(NumberUtil.toInteger(vo.getHn()));
            dayDetail.setPan(NumberUtil.toInteger(vo.getPan()));
            dayDetail.setMode(vo.getMode());
            dayDetail.setWording(vo.getWording());
            dayDetail.setStartingTime(DateUtil.fromString(vo.getStartingTime()));
            dayDetail.setEndingTime(DateUtil.fromString(vo.getEndingTime()));
            return dayDetail;
        }
    }

    @Override
    public DayDetailVo toVo(DayDetail item) {
        if (item == null) {
            return null;
        } else {
            DayDetailVo dayDetailVo = new DayDetailVo();
            dayDetailVo.setId(item.getId());
            dayDetailVo.setHe(NumberUtil.toString(item.getHe()));
            dayDetailVo.setHn(NumberUtil.toString(item.getHn()));
            dayDetailVo.setPan(NumberUtil.toString(item.getPan()));
            dayDetailVo.setMode(item.getMode());
            dayDetailVo.setWording(item.getWording());
            dayDetailVo.setStartingTime(DateUtil.toString(item.getStartingTime()));
            dayDetailVo.setEndingTime(DateUtil.toString(item.getEndingTime()));
            return dayDetailVo;
        }
    }

}
