/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Work;
import com.onda.personnel.rest.vo.WorkVo;

/**
 *
 * @author hp
 */
public class WorkConverter extends AbstractConverter<Work, WorkVo> {

    @Override
    public Work toItem(WorkVo vo) {
        if (vo == null) {
            return null;
        } else {
            Work work = new Work();
            work.setId(vo.getId());
            work.setEmployee(new EmployeeConverter().toItem(vo.getEmployee()));
            work.setWorkDetail(new WorkDetailConverter().toItem(vo.getWorkDetail()));
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
            workVo.setEmployee(new EmployeeConverter().toVo(item.getEmployee()));
            workVo.setWorkDetail(new WorkDetailConverter().toVo(item.getWorkDetail()));
            return workVo;
        }
    }

}
