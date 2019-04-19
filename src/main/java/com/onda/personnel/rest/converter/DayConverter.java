/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Day;
import com.onda.personnel.rest.vo.DayVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class DayConverter extends AbstractConverter<Day, DayVo>{

    @Override
    public Day toItem(DayVo vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DayVo toVo(Day item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
