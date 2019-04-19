/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.rest.vo.DayDetailVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class DayDetailConverter extends AbstractConverter<DayDetail, DayDetailVo>{

    @Override
    public DayDetail toItem(DayDetailVo vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DayDetailVo toVo(DayDetail item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
