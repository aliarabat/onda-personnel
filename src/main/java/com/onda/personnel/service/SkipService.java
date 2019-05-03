/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Skip;

/**
 *
 * @author JaafarDiyaou
 */
public interface SkipService {
          public List<Skip> findByEmployeeMatriculeAndSkipDate(Integer matricule,Date skipDate); 
          public Skip createSkip(Integer matricule,Skip skip);
}
