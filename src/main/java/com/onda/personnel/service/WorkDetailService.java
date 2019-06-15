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

    WorkDetail findByWorkDetailDate(LocalDate localDate);

    WorkDetail findByEmployeeMatriculeAndWorkDetailDate(Integer matricule, int year, int month);

    void saveWorkDetail(WorkDetail workDetail);

    void createWorkDetail(Employee emp, List<Day> days);

    WorkDetail updateWorkDetail(WorkDetail workDetail);
}
