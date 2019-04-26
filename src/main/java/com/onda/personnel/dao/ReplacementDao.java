/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import com.onda.personnel.bean.Replacement;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JaafarDiyaou
 */
@Repository
public interface ReplacementDao extends JpaRepository<Replacement, Long>{
        public List<Replacement> findByOriginalEmployeeMatriculeAndReplacementDate(Integer matricule,Date replacementDate); 
}
