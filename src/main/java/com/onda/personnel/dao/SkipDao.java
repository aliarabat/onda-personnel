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

import com.onda.personnel.model.Skip;

/**
 *
 * @author JaafarDiyaou
 */
@Repository
public interface SkipDao extends JpaRepository<Skip, Long> {
      public List<Skip> findByEmployeeMatriculeAndSkipDate(Integer matricule,Date skipDate); 
}
