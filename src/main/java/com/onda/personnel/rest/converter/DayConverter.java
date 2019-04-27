/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.Timing;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.common.util.StringUtil;
import com.onda.personnel.rest.vo.DayVo;
import com.onda.personnel.rest.vo.TimingVo;
import org.springframework.stereotype.Component;

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
            d.setHe(new TimingConverter().toItem(vo.getHe()));
            d.setHn(new TimingConverter().toItem(vo.getHn()));
            d.setPan(NumberUtil.toInteger(vo.getPan()));
            d.setVacation(new VacationConverter().toItem(vo.getVacationVo()));
            d.setDayDetails(new DayDetailConverter().toItem(vo.getDayDetailsVo()));
            d.setDayDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getDayDate())));
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
            dVo.setHe(new TimingConverter().toVo(item.getHe()));
            dVo.setHn(new TimingConverter().toVo(item.getHn()));
            dVo.setPan(NumberUtil.toString(item.getPan()));
            dVo.setVacationVo(new VacationConverter().toVo(item.getVacation()));
            dVo.setDayDetailsVo(new DayDetailConverter().toVo(item.getDayDetails()));
            dVo.setDayDate(DateUtil.toString(DateUtil.fromDate(item.getDayDate())));
            return dVo;
        }
    }

}