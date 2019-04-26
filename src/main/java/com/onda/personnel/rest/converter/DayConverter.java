/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Day;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.rest.vo.DayVo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 *
 * @author AMINE
 */

@Component
public class DayConverter extends AbstractConverter<Day, DayVo>  {
    @Override
    public Day toItem(DayVo vo) {
        if (vo == null) {
            return null;
        } else {
            Day d = new Day();
            d.setId(vo.getId());
            d.setHe(NumberUtil.toInteger(vo.getHe()));
            d.setHn(NumberUtil.toInteger(vo.getHn()));
            d.setPan(NumberUtil.toInteger(vo.getPan()));
            d.setVacation(new VacationConverter().toItem(vo.getVacationVo()));
            d.setDayDetails(new DayDetailConverter().toItem(vo.getDayDetails()));
            return d;
        }
    }

    @Override
    public DayVo toVo(Day item) {
        if (item == null) {
            return null;
        } else {
            DayVo dVo = new DayVo();
            dVo.setId(item.getId());
            dVo.setHe(NumberUtil.toString(item.getHe()));
            dVo.setHn(NumberUtil.toString(item.getHn()));
            dVo.setPan(NumberUtil.toString(item.getPan()));
            dVo.setVacationVo(new VacationConverter().toVo(item.getVacation()));
            dVo.setDayDetails(new DayDetailConverter().toVo(item.getDayDetails()));
            return dVo;
        }
    }

}
