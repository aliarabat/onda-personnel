/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Replacement;

/**
 *
 * @author JaafarDiyaou
 */
public interface ReplacementService {

    List<Replacement> findByOriginalEmployeeMatriculeAndReplacementDate(Integer matricule, Date replacementDate);

    Replacement createReplacement(Integer matricule, Integer matricule1, String wording, Replacement replacement);

    List<Replacement> findAll();
    
}
