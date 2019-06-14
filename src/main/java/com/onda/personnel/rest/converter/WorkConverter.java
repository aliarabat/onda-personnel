/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import org.springframework.stereotype.Component;

import com.onda.personnel.model.Work;
import com.onda.personnel.rest.vo.WorkVo;

/**
 *
 * @author AMINE
 */
@Component
public class WorkConverter extends AbstractConverter<Work, WorkVo> {

    @Override
    public Work toItem(WorkVo vo) {
        if (vo == null) {
            return null;
        } else {
            Work work = new Work();
            work.setId(vo.getId());
            work.setEmployee(new EmployeeConverter().toItem(vo.getEmployeeVo()));
            work.setWorkDetail(new WorkDetailConverter().toItem(vo.getWorkDetailVo()));
            return work;
        }
    }

    @Override
    public WorkVo toVo(Work item) {
        if (item == null) {
            return null;
        } else {
            WorkVo workVo = new WorkVo();
            workVo.setId(item.getId());
            workVo.setEmployeeVo(new EmployeeConverter().toVo(item.getEmployee()));
            workVo.setWorkDetailVo(new WorkDetailConverter().toVo(item.getWorkDetail()));
            return workVo;
        }
    }


}
