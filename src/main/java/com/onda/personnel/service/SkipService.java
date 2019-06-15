/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Skip;

/**
 *
 * @author JaafarDiyaou
 */
public interface SkipService {

    List<Skip> findByEmployeeMatriculeAndSkipDate(Integer matricule, Date skipDate);

    Skip createSkip(Integer matricule, String wording, Skip skip);

    int updateSkip(DayDetail dayDetail);

    List<Skip> findAllSkips();

    int removeSkip(Long id);

}
