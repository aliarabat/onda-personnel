/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import com.onda.personnel.bean.Work;
import java.util.Date;
import java.util.List;

import com.onda.personnel.bean.WorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface WorkDao extends JpaRepository<Work, Long>{
    
    Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date workDetailDate);
    
    Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule);

    List<Work> findAllByEmployeeMatricule(Integer matricule);

    List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Date date1, Date date2);

    List<Work> findByWorkDetailWorkDetailDateBetween(Date date1, Date date2);

    //List<WorkDetail> findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date t);
}
