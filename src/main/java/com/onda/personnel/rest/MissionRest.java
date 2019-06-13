/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.model.Mission;
import com.onda.personnel.rest.converter.DayDetailConverter;
import com.onda.personnel.rest.converter.MissionConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.rest.vo.MissionVo;
import com.onda.personnel.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JaafarDiyaou
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/personnel-api/personnels/mission")
public class MissionRest {
    @Autowired
    MissionService missionService;
    
    
    @PutMapping("/")
    public int updateMission(@RequestBody DayDetailVo dayDetailVo) {
        return missionService.updateMission(new DayDetailConverter().toItem(dayDetailVo));
    }
    
    
    
    

    public MissionService getMissionService() {
        return missionService;
    }

    public void setMissionService(MissionService missionService) {
        this.missionService = missionService;
    }
    
    
    
    
    
    
    
    
    
}
