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

/**
 *
 * @author AMINE
 */
@Repository
public interface WorkDao extends JpaRepository<Work, Long> {

    Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date workDetailDate);

    Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule);

    List<Work> findAllByEmployeeMatricule(Integer matricule);

    List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetweenOrderByWorkDetailWorkDetailDateAsc(
            Integer matricule, Date date1, Date date2);

    List<Work> findByWorkDetailWorkDetailDateBetweenOrderByEmployeeMatriculeAscWorkDetailWorkDetailDateAsc(Date date1,
            Date date2);

    List<Work> findByWorkDetailWorkDetailDateOrderByEmployeeMatriculeAscWorkDetailWorkDetailDateAsc(
            Date workDetailDate);

    Work findTopByWorkDetailWorkDetailDateOrderByWorkDetailWorkDetailDateDesc(Date date);

    List<Work> findByEmployeeMatriculeAndWorkDetailWorkDetailDateBetweenOrderByWorkDetailWorkDetailDateAsc(
            int matricule, Date dateDebut, Date dateFin);

    Work findTopByEmployeeMatriculeAndWorkDetailWorkDetailDateBetweenOrderByIdAsc(Integer matricule, Date dateDebut, Date dateFin);

}
