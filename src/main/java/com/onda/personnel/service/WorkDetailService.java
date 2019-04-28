/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.rest.vo.WorkDetailVo;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface WorkDetailService {

    public WorkDetail findByWorkDetailDate(LocalDate localDate);

    public void saveWorkDetail(WorkDetail workDetail);

    public void createWorkDetail(Employee emp, List<Day> days);

    WorkDetail updateWorkDetail(WorkDetail workDetail);
}
