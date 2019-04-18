/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.rest.vo.WorkDetailVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class WorkDetailConverter extends AbstractConverter<WorkDetail, WorkDetailVo>{

    @Override
    public WorkDetail toItem(WorkDetailVo vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WorkDetailVo toVo(WorkDetail item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
