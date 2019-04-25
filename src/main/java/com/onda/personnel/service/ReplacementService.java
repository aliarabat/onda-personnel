/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Replacement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JaafarDiyaou
 */
public interface ReplacementService {
              public List<Replacement> findByOriginalEmployeeMatriculeAndReplacementDate(Integer matricule,Date replacementDate); 
              public int createReplacement(Replacement replacement);
}
