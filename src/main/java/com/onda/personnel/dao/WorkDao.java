/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.personnel.model.Work;
import com.onda.personnel.model.WorkDetail;

/**
 *
 * @author AMINE
 */
@Repository
public interface WorkDao extends JpaRepository<Work, Long> {

    Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date workDetailDate);

    Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule);

    List<Work> findAllByEmployeeMatricule(Integer matricule);

    List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Date date1, Date date2);

    List<Work> findByWorkDetailWorkDetailDateBetween(Date date1, Date date2);

    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate);

    //List<WorkDetail> findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date t);
}
