/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface WorkService {

    public void saveWork(Work work);

    public void createWork(Employee emp, WorkDetail workDetail);

    public Work findByEmployeeMatriculeAndWorkDetailTestDate(Integer matricule, Date workDetailDate);
    
    public Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule);

    List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Integer annee);

    List<Work> findAllByWorkDetailWorkDetailDateBetween(Integer annee);
}
