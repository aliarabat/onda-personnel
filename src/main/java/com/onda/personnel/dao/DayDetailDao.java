/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.personnel.model.DayDetail;

/**
 *
 * @author AMINE
 */
@Repository
public interface DayDetailDao extends JpaRepository<DayDetail, Long>{
    
    
}
