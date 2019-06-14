/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;



import java.time.LocalDate;
import java.util.List;

import com.onda.personnel.model.Day;
import com.onda.personnel.model.Employee;
import com.onda.personnel.model.WorkDetail;

/**
 *
 * @author AMINE
 */
public interface WorkDetailService {

    public WorkDetail findByWorkDetailDate(LocalDate localDate);

    public WorkDetail findByEmployeeMatriculeAndWorkDetailDate(Integer matricule, int year,int month);

    public void saveWorkDetail(WorkDetail workDetail);

    public void createWorkDetail(Employee emp, List<Day> days);

    WorkDetail updateWorkDetail(WorkDetail workDetail);
}
