/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.model.DayDetail;
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
            dayDetail.setDetail(new DetailConverter().toItem(vo.getDetailVo()));
            dayDetail.setReplacement(new ReplacementConverter().toItem(vo.getReplacementVo()));
            dayDetail.setSkip(new SkipConverter().toItem(vo.getSkipVo()));
            dayDetail.setMission(new MissionConverter().toItem(vo.getMissionVo()));
            return dayDetail;
        }
    }

    @Override
    public DayDetailVo toVo(DayDetail item) {
        if (item == null) {
            return null;
        } else {
            DayDetailVo dVo = new DayDetailVo();
            dVo.setId(item.getId());
            dVo.setDetailVo(new DetailConverter().toVo(item.getDetail()));
            dVo.setReplacementVo(new ReplacementConverter().toVo(item.getReplacement()));
            dVo.setSkipVo(new SkipConverter().toVo(item.getSkip()));
            dVo.setMissionVo(new MissionConverter().toVo(item.getMission()));
            return dVo;
        }
    }

}
