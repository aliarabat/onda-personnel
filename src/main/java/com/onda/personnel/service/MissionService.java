/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.model.DayDetail;
import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Mission;

/**
 *
 * @author AMINE
 */
public interface MissionService {

    public Mission createMisssion(Integer matricule,String wording, Mission mission);

    public List<Mission> findByEmployeeMatriculeAndStartingDate(Integer matricule, Date startingDate);
    
    public int updateMission(DayDetail dayDetail);

}
