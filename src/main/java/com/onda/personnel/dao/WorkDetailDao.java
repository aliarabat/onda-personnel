/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.personnel.model.WorkDetail;

/**
 *
 * @author AMINE
 */
@Repository

public interface WorkDetailDao extends JpaRepository<WorkDetail, Long> {

    public WorkDetail findByWorkDetailDate(LocalDate localDate);

    //public List<WorkDetail> findByWorkDetailDate(LocalDate workDetailDate);

}
