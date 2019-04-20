/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.WorkDetail;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface WorkDetailSevice {

    public WorkDetail findByWorkDetailDate(LocalDate localDate);

    public void saveWorkDetail(WorkDetail workDetail);

    public void createWorkDetail(Employee emp, LocalDate workDetailDate, List<Day> days);
    // public List<WorkDetail> findByWorkDetailDate(LocalDate workDetailDate);
}
