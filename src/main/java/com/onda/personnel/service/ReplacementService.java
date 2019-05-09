/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.model.DayDetail;
import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Replacement;

/**
 *
 * @author JaafarDiyaou
 */
public interface ReplacementService {

    public List<Replacement> findByOriginalEmployeeMatriculeAndReplacementDate(Integer matricule, Date replacementDate);

    public Replacement createReplacement(Integer matricule, Integer matricule1, String wording, Replacement replacement);


}
