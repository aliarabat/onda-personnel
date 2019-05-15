/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.util.DateUtil;
import com.onda.personnel.util.NumberUtil;
import com.onda.personnel.util.StringUtil;
import com.onda.personnel.model.Detail;
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
            dayDetail.setHe(new TimingConverter().toItem(vo.getHeVo()));
            dayDetail.setHn(new TimingConverter().toItem(vo.getHnVo()));
            dayDetail.setPan(NumberUtil.toInteger(vo.getPan()));
            dayDetail.setMode(vo.getMode());
            dayDetail.setWording(vo.getWording());
            dayDetail.setReference(vo.getReference());
            dayDetail.setStartingTime(new TimingConverter().toItem(vo.getStartingTimeVo()));
            dayDetail.setEndingTime(new TimingConverter().toItem(vo.getEndingTimeVo()));
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
            dayDetailVo.setHeVo(new TimingConverter().toVo(item.getHe()));
            dayDetailVo.setHnVo(new TimingConverter().toVo(item.getHn()));
            dayDetailVo.setPan(NumberUtil.toString(item.getPan()));
            dayDetailVo.setMode(item.getMode());
            dayDetailVo.setWording(item.getWording());
            dayDetailVo.setReference(item.getReference());
            dayDetailVo.setStartingTimeVo(new TimingConverter().toVo(item.getStartingTime()));
            dayDetailVo.setEndingTimeVo(new TimingConverter().toVo(item.getEndingTime()));
            return dayDetailVo;
        }
    }

}
