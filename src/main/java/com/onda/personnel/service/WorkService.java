/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.onda.personnel.model.Work;
import com.onda.personnel.rest.vo.WorkVo;

/**
 *
 * @author AMINE
 */
public interface WorkService {

    void saveWork(Work work);

    Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date workDetailDate);

    List<Work> findWorksByDate(Date workDate);

    Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule);

    List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Integer annee);

    List<Work> findAllByWorkDetailWorkDetailDateBetween(Integer annee);

    List<Work> findByEmployeeMatriculeAndMonthAndYear(Integer matricule, int year, int month);

    List<Work> findByMonthAndYear(int year, int month);

    List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate);

    List<String> findFromDateToDate(Integer matricule);

    void printDoc(HttpServletResponse response, Integer annee, Integer month);

    void printXlsx(HttpServletResponse response, Integer year, Integer month);

    void printGraphForEmployee(HttpServletResponse response, int matricule, int year);

    Work findTopByWorkDetailWorkDetailDateOrderByWorkDetailWorkDetailDateDesc(Date date);

    Work findTopByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, int year);

    List<Work> findAll();

    List<WorkVo> countAll(int year);

    int deleteById(long id);
}
