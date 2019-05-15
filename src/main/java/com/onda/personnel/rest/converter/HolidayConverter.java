package com.onda.personnel.rest.converter;

import org.springframework.stereotype.Component;

import com.onda.personnel.util.DateUtil;
import com.onda.personnel.model.Holiday;
import com.onda.personnel.rest.vo.HolidayVo;

@Component
public class HolidayConverter extends AbstractConverter<Holiday, HolidayVo> {

    @Override
    public Holiday toItem(HolidayVo vo) {
        if (vo == null) {
            return null;
        } else {
            Holiday item = new Holiday();
            item.setId(vo.getId());
            item.setReference(vo.getReference());
            item.setStartingDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getStartingDate())));
            item.setEndingDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(vo.getEndingDate())));
            return item;
        }
    }

    @Override
    public HolidayVo toVo(Holiday item) {
        if (item == null) {
            return null;
        } else {
            HolidayVo vo = new HolidayVo();
            vo.setId(item.getId());
            vo.setReference(item.getReference());
            vo.setStartingDate(DateUtil.toString(DateUtil.fromDate(item.getStartingDate())));
            vo.setEndingDate(DateUtil.toString(DateUtil.fromDate(item.getEndingDate())));
            return vo;
        }
    }

}
