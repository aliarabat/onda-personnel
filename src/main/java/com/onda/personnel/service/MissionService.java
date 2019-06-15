/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Mission;

/**
 *
 * @author AMINE
 */
public interface MissionService {

    Mission createMisssion(Integer matricule, Mission mission);

    List<Mission> findByEmployeeMatriculeAndStartingDate(Integer matricule, Date startingDate);

    int updateMission(DayDetail dayDetail);

}
