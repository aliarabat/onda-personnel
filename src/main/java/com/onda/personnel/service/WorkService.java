/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.model.Work;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface WorkService {

    void saveWork(Work work);

    Work findByEmployeeMatriculeAndWorkDetailTestDate(Integer matricule, Date workDetailDate);

    List<Work> findWorksByDate( Date workDate);

    Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule);

    List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Integer annee);

    List<Work> findAllByWorkDetailWorkDetailDateBetween(Integer annee);

    public Work findByEmployeeMatriculeAndMonthAndYear(Integer matricule, int year, int month);

    public List<Work> findByMonthAndYear(int year, int month);

    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate);

    List<String> findFromDateToDate(Integer matricule);
    
    List<Work> findAll();
}
