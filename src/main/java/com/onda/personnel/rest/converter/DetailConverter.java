/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Detail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.rest.vo.DetailVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class DetailConverter extends AbstractConverter<Detail, DetailVo> {

    @Override
    public Detail toItem(DetailVo vo) {
        if (vo == null) {
            return null;
        } else {
            Detail dayDetail = new Detail();
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
    public DetailVo toVo(Detail item) {
        if (item == null) {
            return null;
        } else {
            DetailVo dayDetailVo = new DetailVo();
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
