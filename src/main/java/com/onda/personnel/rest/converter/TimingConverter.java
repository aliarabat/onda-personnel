/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import org.springframework.stereotype.Component;

import com.onda.personnel.model.Timing;
import com.onda.personnel.rest.vo.TimingVo;
import com.onda.personnel.util.NumberUtil;

/**
 * @author AMINE
 */
@Component
public class TimingConverter extends AbstractConverter<Timing, TimingVo> {

    @Override
    public Timing toItem(TimingVo vo) {
        if (vo == null) {
            return null;
        } else {
            Timing timing = new Timing();
            timing.setId(vo.getId());
            timing.setHour(NumberUtil.toInt(vo.getHour()));
            timing.setMinute(NumberUtil.toInt(vo.getMinute()));
            return timing;
        }
    }

    @Override
    public TimingVo toVo(Timing item) {
        if (item == null) {
            return null;
        } else {
            TimingVo timingVo = new TimingVo();
            timingVo.setId(item.getId());
            timingVo.setHour(NumberUtil.fromIntToString(item.getHour()));
            timingVo.setMinute(NumberUtil.fromIntToString(item.getMinute()));
            return timingVo;
        }
    }
}
